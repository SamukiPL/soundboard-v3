package me.samuki.feature.list.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.core.ui.previews.PreviewSoundProvider
import me.samuki.feature.list.items.PlayableItem
import me.samuki.model.mapper.key

internal class PreviewPlayableItemProvider(
    private val previewSoundProvider: PreviewSoundProvider = PreviewSoundProvider()
) : PreviewParameterProvider<PlayableItem> {

    override val values: Sequence<PlayableItem>
        get() = previewSoundProvider.values.first().let { sound ->
            sequenceOf(
                PlayableItem(
                    key = sound.key,
                    name = sound.name,
                    playable = sound,
                    likeable = sound,
                ),
                PlayableItem(
                    key = sound.key,
                    name = sound.name,
                    playable = sound,
                    likeable = null
                ),
            )
        }
}
