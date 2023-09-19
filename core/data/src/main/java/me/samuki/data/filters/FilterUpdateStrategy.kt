package me.samuki.data.filters

import me.samuki.model.filters.Filter
import me.samuki.model.values.FilterSelected

internal interface FilterUpdateStrategy {

    fun updateFilters(
        filters: List<Filter>,
        oldFilter: Filter,
        newState: FilterSelected
    ): List<Filter>
}
