package me.samuki.data.filters.query

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.samuki.domain.search.ObserveSearchQuery
import me.samuki.model.filters.Filter
import me.samuki.model.filters.FilterOrder
import me.samuki.model.filters.QueryFilter
import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id
import me.samuki.model.values.Query
import javax.inject.Inject

@ViewModelScoped
internal class QueryFilterDataSource @Inject constructor(
    private val observeSearchQuery: ObserveSearchQuery
) {

    fun provideFilterFlow(): Flow<List<Filter>> = observeSearchQuery().map { query ->
        when (query) {
            Query.Empty -> null
            is Query.Text -> QueryFilter(
                order = FilterOrder.Query,
                id = Id(1),
                selected = FilterSelected(true),
                query = query
            )
        }
    }.map { listOfNotNull(it) }
}
