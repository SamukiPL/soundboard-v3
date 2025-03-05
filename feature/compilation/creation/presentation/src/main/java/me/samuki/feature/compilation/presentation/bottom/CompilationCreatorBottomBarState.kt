package me.samuki.feature.compilation.presentation.bottom

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import me.samuki.core.ui.composables.inputs.QueryViewState
import me.samuki.feature.compilation.presentation.bottom.pause.PausePickerItem
import me.samuki.feature.compilation.presentation.bottom.pause.generatePauseItems

internal enum class BottomBarType {
    Pause, Query
}

@Stable
internal class BottomBarState(
    pauseItems: List<PausePickerItem> = generatePauseItems(),
    queryViewState: QueryViewState = QueryViewState()
) {
    val pauseItems: List<PausePickerItem> by mutableStateOf(pauseItems)
    val queryViewState: QueryViewState by mutableStateOf(queryViewState)
}
