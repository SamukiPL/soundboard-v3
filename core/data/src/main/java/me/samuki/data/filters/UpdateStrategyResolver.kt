package me.samuki.data.filters

import me.samuki.data.filters.listType.ListTypeUpdateStrategy
import me.samuki.data.filters.query.QueryFilterUpdateStrategy
import me.samuki.model.filters.Filter
import me.samuki.model.filters.ListTypeFilter
import me.samuki.model.filters.QueryFilter
import me.samuki.model.values.FilterSelected
import javax.inject.Inject

internal class UpdateStrategyResolver @Inject constructor(
    private val listTypeUpdateStrategy: ListTypeUpdateStrategy,
    private val queryFilterUpdateStrategy: QueryFilterUpdateStrategy
) : FilterUpdateStrategy {

    override fun updateFilters(
        filters: List<Filter>,
        oldFilter: Filter,
        newState: FilterSelected
    ): List<Filter> = when (oldFilter) {
        is ListTypeFilter -> listTypeUpdateStrategy.updateFilters(filters, oldFilter, newState)
        is QueryFilter -> queryFilterUpdateStrategy.updateFilters(filters, oldFilter, newState)
    }
}
