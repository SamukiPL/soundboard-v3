package me.samuki.feature.compilation.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.core.ui.previews.PreviewSoundProvider
import me.samuki.feature.compilation.presentation.items.sounds.CompilationCreatorSound
import me.samuki.model.mapper.key

internal class PreviewCompilationCreatorSoundProvider(
    private val previewSoundProvider: PreviewSoundProvider = PreviewSoundProvider()
) : PreviewParameterProvider<CompilationCreatorSound> {

    override val values: Sequence<CompilationCreatorSound>
        get() = previewSoundProvider.values.map { sound ->
            CompilationCreatorSound(
                key = sound.key,
                name = sound.name,
                sound = sound
            )
        }
}
