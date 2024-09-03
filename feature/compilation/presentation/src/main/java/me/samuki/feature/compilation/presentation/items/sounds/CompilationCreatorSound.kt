package me.samuki.feature.compilation.presentation.items.sounds

import androidx.compose.runtime.Stable
import me.samuki.model.Key
import me.samuki.model.Sound
import me.samuki.model.values.Name
import me.samuki.model.values.Supplement

@Stable
internal data class CompilationCreatorSound(
    val key: Key,
    val name: Name,
    val sound: Sound,
)
