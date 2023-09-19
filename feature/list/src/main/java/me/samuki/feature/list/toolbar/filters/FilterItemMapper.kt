package me.samuki.feature.list.toolbar.filters

import me.samuki.model.filters.Filter
import me.samuki.model.filters.ListTypeFilter
import me.samuki.model.filters.QueryFilter
import me.samuki.model.values.Name
import me.samuki.model.values.getQueryValue

internal fun Filter.toFilterItem(): FilterItem = when (this) {
    is ListTypeFilter -> toListTypeFilterItem()
    is QueryFilter -> toQueryFilterItem()
}

internal fun ListTypeFilter.toListTypeFilterItem() = ListTypeFilterItem(
    order = order,
    id = id,
    name = Name(listType.name),
    selected = selected,
    filter = this
)

internal fun QueryFilter.toQueryFilterItem() = QueryFilterItem(
    order = order,
    id = id,
    name = Name(query.getQueryValue()),
    query = this.query,
    filter = this,
)
