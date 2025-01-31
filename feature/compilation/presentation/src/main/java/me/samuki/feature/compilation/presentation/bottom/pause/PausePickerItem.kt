package me.samuki.feature.compilation.presentation.bottom.pause

import androidx.compose.runtime.Stable
import me.samuki.model.Pause

@Stable
internal data class PausePickerItem(
    val repeats: Int,
    val text: String,
)

internal fun generatePauseItems(): List<PausePickerItem> = listOf(PausePickerItem(-1, "")) +
        (1..100).map { repeats ->
            PausePickerItem(
                repeats = repeats,
                text = repeats.toBigDecimal().movePointLeft(1).toString()
            )
        } + listOf(PausePickerItem(9999, ""))

internal val PausePickerItem.pause
    get() = Pause(repeats)
