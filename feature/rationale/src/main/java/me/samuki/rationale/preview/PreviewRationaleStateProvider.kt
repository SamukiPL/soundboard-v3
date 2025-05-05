package me.samuki.rationale.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.rationale.RationaleContract

internal class PreviewRationaleStateProvider : PreviewParameterProvider<RationaleContract.State> {
    override val values: Sequence<RationaleContract.State>
        get() = sequenceOf(
            RationaleContract.State(
                screenState = RationaleContract.ScreenState.PERMISSION_REQUEST
            ),
            RationaleContract.State(
                screenState = RationaleContract.ScreenState.SUCCESS
            )
        )
} 