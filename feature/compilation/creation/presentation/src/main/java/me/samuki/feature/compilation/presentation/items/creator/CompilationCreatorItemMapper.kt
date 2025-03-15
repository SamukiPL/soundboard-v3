package me.samuki.feature.compilation.presentation.items.creator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import me.samuki.core.ui.value.Label
import me.samuki.feature.compilation.creation.presentation.R
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.Pause
import me.samuki.model.Sound

internal fun CombinedCombinable.toItem(): CompilationCreatorItem = CompilationCreatorItem(
    id = id,
    combinedCombinable = this
)

internal val CompilationCreatorItem.label: Label
    @Composable
    get() = when (val combinable = combinedCombinable.combinable) {
        is Pause -> stringResource(
            id = R.string.pause_combinable_chip,
            combinable.repeats * REPEAT_SECOND_VALUE
        )
        is Sound -> combinable.name.value
    }.let { labelString ->
        Label(labelString)
    }

private const val REPEAT_SECOND_VALUE = 0.1F
