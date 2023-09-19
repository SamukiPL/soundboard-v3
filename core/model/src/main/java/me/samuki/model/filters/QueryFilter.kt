package me.samuki.model.filters

import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id
import me.samuki.model.values.Query

public data class QueryFilter(
    override val order: FilterOrder,
    override val id: Id,
    override val selected: FilterSelected,
    public val query: Query
) : Filter {

    override fun modifySelection(selected: FilterSelected): Filter = copy(
        selected = selected
    )
}
