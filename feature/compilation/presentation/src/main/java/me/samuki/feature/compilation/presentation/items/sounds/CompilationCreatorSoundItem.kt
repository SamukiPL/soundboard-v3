package me.samuki.feature.compilation.presentation.items.sounds

import me.samuki.model.Key
import me.samuki.model.Sound
import me.samuki.model.values.Name

internal data class CompilationCreatorSoundItem(
    val key: Key,
    val name: Name,
    val sound: Sound,
)
