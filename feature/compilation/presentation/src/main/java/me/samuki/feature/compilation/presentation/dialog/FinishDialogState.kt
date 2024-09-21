package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.model.util.emptyName
import me.samuki.model.values.Name

internal typealias ShowNamePlaceholder = Boolean
internal typealias FinishCreationEnabled = Boolean

@Stable
internal class FinishDialogState(
    name: Name = emptyName(),
    showNamePlaceholder: ShowNamePlaceholder = true,
    finishDialogState: FinishCreationEnabled = false
) {
    var name: Name by mutableStateOf(name)
    var showNamePlaceholder: ShowNamePlaceholder by mutableStateOf(showNamePlaceholder)
    var finishDialogState: FinishCreationEnabled by mutableStateOf(finishDialogState)
}
