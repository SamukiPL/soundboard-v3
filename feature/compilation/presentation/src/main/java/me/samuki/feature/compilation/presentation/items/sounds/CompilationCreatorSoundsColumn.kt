package me.samuki.feature.compilation.presentation.items.sounds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract

@Composable
internal fun CompilationCreatorSoundsColumn(
    sounds: List<CompilationCreatorSound>,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sounds, key = { it.key.toString() }) {
            CompilationCreatorSoundView(
                compilationCreatorSound = it,
                onEvent = onEvent
            )
        }
    }
}
