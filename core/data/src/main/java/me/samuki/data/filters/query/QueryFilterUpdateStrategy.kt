package me.samuki.data.filters.query

import me.samuki.data.filters.FilterUpdateStrategy
import me.samuki.model.filters.Filter
import me.samuki.model.filters.QueryFilter
import me.samuki.model.values.FilterSelected
import javax.inject.Inject

internal class QueryFilterUpdateStrategy @Inject constructor() : FilterUpdateStrategy {

    override fun updateFilters(
        filters: List<Filter>,
        oldFilter: Filter,
        newState: FilterSelected
    ): List<Filter> = when (newState.value) {
        true -> filters.map { filter ->
            return@map if (filter.id == oldFilter.id && filter.order == oldFilter.order)
                oldFilter
            else
                filter
        }
        false -> filters.filter { it !is QueryFilter }
    }
}
