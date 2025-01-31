package me.samuki.feature.compilation.presentation.items.creator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.preview.PreviewCompilationCreatorItemProvider

@Composable
internal fun CompilationCreatorItemView(
    combinedItem: CompilationCreatorItem,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedAssistChip(
        onClick = {
            onEvent(
                CreatorContract.Event.RemoveCombinable(
                    combinedItem.combinedCombinable
                )
            )
        },
        label = {
            Text(text = combinedItem.label.value)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onEvent(
                            CreatorContract.Event.RemoveCombinable(
                                combinedItem.combinedCombinable
                            )
                        )
                    }
            )
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CompilationCreatorItemViewSoundPreview(
    @PreviewParameter(PreviewCompilationCreatorItemProvider::class) combinable: CompilationCreatorItem
) {
    CompilationCreatorItemView(
        combinedItem = combinable,
        onEvent = {},
    )
}
