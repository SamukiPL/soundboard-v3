package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.samuki.feature.compilation.presentation.CreatorContract

@Composable
internal fun CompilationCreationFinishButton(
    finishCreationEnabled: FinishCreationEnabled,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = finishCreationEnabled,
        modifier = modifier,
    ) {
        FloatingActionButton(
            onClick = {
                onEvent(
                    CreatorContract.Event.EndCreation,
                )
            },
            modifier = Modifier
        ) {
            Text(
                text = "Finish",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
