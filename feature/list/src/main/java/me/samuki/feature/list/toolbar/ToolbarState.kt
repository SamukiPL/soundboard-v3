package me.samuki.feature.list.toolbar

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.core.ui.composables.QueryViewState
import me.samuki.feature.list.toolbar.filters.FilterItem

internal enum class ToolbarType {
    Filters, QueryInput
}

@Stable
internal class ToolbarState(
    filters: List<FilterItem> = emptyList(),
    type: ToolbarType = ToolbarType.Filters,
    showQueryButton: QueryButtonVisible = true,
    queryViewState: QueryViewState = QueryViewState(),
) {
    var filters: List<FilterItem> by mutableStateOf(filters)
    var type: ToolbarType by mutableStateOf(type)
    var showQueryButton: QueryButtonVisible by mutableStateOf(showQueryButton)
    val queryViewState: QueryViewState by mutableStateOf(queryViewState)
}
