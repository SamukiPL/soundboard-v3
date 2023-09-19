package me.samuki.feature.list.toolbar.filters

import androidx.compose.runtime.Stable
import me.samuki.model.filters.Filter
import me.samuki.model.filters.FilterOrder
import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Query

internal sealed interface FilterItem {
    val order: FilterOrder
    val id: Id
}

@Stable
internal data class ListTypeFilterItem(
    override val order: FilterOrder,
    override val id: Id,
    val name: Name,
    val selected: FilterSelected,
    val filter: Filter
) : FilterItem

@Stable
internal data class QueryFilterItem(
    override val order: FilterOrder,
    override val id: Id,
    val name: Name,
    val query: Query,
    val filter: Filter
) : FilterItem
