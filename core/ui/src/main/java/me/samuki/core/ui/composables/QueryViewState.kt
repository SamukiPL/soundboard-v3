package me.samuki.core.ui.composables

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.model.values.Query

@Stable
public class QueryViewState(
    query: Query = Query.Empty,
    acceptQueryEnable: Boolean = false,
    showQueryPlaceholder: Boolean = true,
) {
    public var query: Query by mutableStateOf(query)
    public var acceptQueryEnable: Boolean by mutableStateOf(acceptQueryEnable)
    public var showQueryPlaceholder: Boolean by mutableStateOf(showQueryPlaceholder)
}
