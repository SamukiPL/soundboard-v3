package me.samuki.feature.list.toolbar

import androidx.compose.runtime.Stable
import me.samuki.feature.list.toolbar.filters.FilterItem
import me.samuki.model.values.Query

internal typealias QueryButtonVisible = Boolean
internal typealias AcceptQueryEnable = Boolean
internal typealias ShowPlaceholder = Boolean

internal enum class ToolbarType {
    Filters, QueryInput
}

@Stable
internal data class ToolbarState(
    val type: ToolbarType = ToolbarType.Filters,
    val filters: List<FilterItem> = emptyList(),
    val showQueryButton: QueryButtonVisible = true,
    val query: Query = Query.Empty,
    val acceptQueryEnable: AcceptQueryEnable = false,
    val showQueryPlaceholder: ShowPlaceholder = true
)
