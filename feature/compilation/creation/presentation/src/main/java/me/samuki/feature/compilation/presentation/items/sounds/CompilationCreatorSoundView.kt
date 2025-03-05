package me.samuki.feature.compilation.presentation.items.sounds

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.preview.PreviewCompilationCreatorSoundProvider

@Composable
internal fun CompilationCreatorSoundView(
    compilationCreatorSound: CompilationCreatorSound,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(1.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onEvent(
                    CreatorContract.Event.AddSound(
                        compilationCreatorSound.sound
                    )
                )
            }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = compilationCreatorSound.name.value,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .animateContentSize()
                .weight(1f)
        )
    }
}

@Preview
@Composable
private fun CompilationCreatorSoundViewPreview(
    @PreviewParameter(
        PreviewCompilationCreatorSoundProvider::class,
        limit = 1
    ) sound: CompilationCreatorSound
) {
    CompilationCreatorSoundView(
        compilationCreatorSound = sound,
        onEvent = {}
    )
}
