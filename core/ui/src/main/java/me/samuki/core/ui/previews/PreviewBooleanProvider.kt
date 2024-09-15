package me.samuki.core.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

public class PreviewBooleanProvider : PreviewParameterProvider<Boolean> {

    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )
}
