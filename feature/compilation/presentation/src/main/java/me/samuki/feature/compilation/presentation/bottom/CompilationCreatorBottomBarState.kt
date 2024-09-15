package me.samuki.feature.compilation.presentation.bottom

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import me.samuki.core.ui.composables.QueryViewState

internal enum class BottomBarType {
    Pause, Query
}

@Stable
internal class BottomBarState(
    queryViewState: QueryViewState = QueryViewState()
) {
    val queryViewState: QueryViewState by mutableStateOf(queryViewState)
}
