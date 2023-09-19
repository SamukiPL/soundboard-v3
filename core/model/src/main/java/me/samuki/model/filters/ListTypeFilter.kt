package me.samuki.model.filters

import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id

public data class ListTypeFilter(
    override val order: FilterOrder,
    override val id: Id,
    override val selected: FilterSelected,
    public val listType: ListType
) : Filter {

    override fun modifySelection(selected: FilterSelected): Filter = copy(
        selected = selected
    )
}
