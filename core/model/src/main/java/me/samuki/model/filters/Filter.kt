package me.samuki.model.filters

import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id

public sealed interface Filter {
    public val order: FilterOrder
    public val id: Id
    public val selected: FilterSelected

    public fun modifySelection(selected: FilterSelected): Filter
}
