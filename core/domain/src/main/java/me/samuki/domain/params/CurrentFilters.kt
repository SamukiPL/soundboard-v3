package me.samuki.domain.params

import me.samuki.model.filters.ListType
import me.samuki.model.values.Query

public data class CurrentFilters(
    val listType: ListType,
    val query: Query,
)
