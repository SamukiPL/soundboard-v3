package me.samuki.core.ui.composables.inputs

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.model.values.Query

public typealias AcceptQueryEnable = Boolean
public typealias ShowPlaceholder = Boolean

@Stable
public class QueryViewState(
    query: Query = Query.Empty,
    acceptQueryEnable: AcceptQueryEnable = false,
    showQueryPlaceholder: ShowPlaceholder = true,
) {
    public var query: Query by mutableStateOf(query)
    public var acceptQueryEnable: Boolean by mutableStateOf(acceptQueryEnable)
    public var showQueryPlaceholder: Boolean by mutableStateOf(showQueryPlaceholder)
}
