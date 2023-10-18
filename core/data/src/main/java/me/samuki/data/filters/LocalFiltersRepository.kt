package me.samuki.data.filters

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import me.samuki.common.rail.runNoAnswer
import me.samuki.data.filters.listType.ListTypeFiltersDataSource
import me.samuki.data.filters.query.QueryFilterDataSource
import me.samuki.domain.filters.FiltersRepository
import me.samuki.model.NoAnswer
import me.samuki.model.filters.Filter
import me.samuki.model.values.FilterSelected
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalFiltersRepository @Inject constructor(
    listTypeFiltersDataSource: ListTypeFiltersDataSource,
    private val queryFilterDataSource: QueryFilterDataSource,
    private val updateStrategyResolver: UpdateStrategyResolver
) : FiltersRepository {

    private val filterComparator: Comparator<Filter>
        get() = compareBy<Filter> {
            it.selected.value.not()
        }.thenComparingInt {
            it.order.value
        }.thenComparingInt {
            it.id.value
        }

    private val _allFilters: MutableStateFlow<List<Filter>>
    override val allFilters: Flow<List<Filter>>
        get() = _allFilters.combine(queryFilterDataSource.provideFilterFlow()) { filters, query ->
            (filters + query).sortedWith(filterComparator)
        }

    init {
        val listTypes = listTypeFiltersDataSource.provideListTypesFilters()
        _allFilters = MutableStateFlow(listTypes)
    }

    private fun updateFilter(
        oldFilter: Filter,
        newState: FilterSelected
    ): Result<NoAnswer> = runNoAnswer {
        _allFilters.update { list ->
            updateStrategyResolver.updateFilters(list, oldFilter, newState)
        }
    }

    override suspend fun selectFilter(filter: Filter): Result<NoAnswer> = updateFilter(
        oldFilter = filter,
        newState = FilterSelected(SELECTED)
    )

    override suspend fun removeFilter(filter: Filter): Result<NoAnswer> = updateFilter(
        oldFilter = filter,
        newState = FilterSelected(NOT_SELECTED)
    )

    private companion object {
        const val SELECTED = true
        const val NOT_SELECTED = false
    }
}
