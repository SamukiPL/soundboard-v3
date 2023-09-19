package me.samuki.data.filters.listType

import me.samuki.data.filters.FilterUpdateStrategy
import me.samuki.model.filters.Filter
import me.samuki.model.values.FilterSelected
import javax.inject.Inject

internal class ListTypeUpdateStrategy @Inject constructor() : FilterUpdateStrategy {

    override fun updateFilters(
        filters: List<Filter>,
        oldFilter: Filter,
        newState: FilterSelected
    ): List<Filter> = filters.map { filter ->
        return@map if (filter.order == oldFilter.order)
            filter.modifySelection(FilterSelected(filter.id == oldFilter.id))
        else
            filter
    }
}
