package me.samuki.data.filters.listType

import me.samuki.model.filters.Filter
import me.samuki.model.filters.FilterOrder
import me.samuki.model.filters.ListType
import me.samuki.model.filters.ListTypeFilter
import me.samuki.model.values.FilterSelected
import me.samuki.model.values.Id
import javax.inject.Inject
import kotlin.enums.EnumEntries

internal class ListTypeFiltersDataSource @Inject constructor(
    private val listTypes: EnumEntries<ListType>
) {

    fun provideListTypesFilters(): List<Filter> = listTypes.mapIndexed { index, type ->
        ListTypeFilter(
            order = FilterOrder.ListType,
            id = Id(index),
            selected = FilterSelected(ListType.All == type),
            listType = type
        )
    }
}
