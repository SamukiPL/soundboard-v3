package me.samuki.feature.compilation.presentation.items.sounds

import me.samuki.model.Compilation
import me.samuki.model.Playable
import me.samuki.model.Sound
import me.samuki.model.mapper.key

internal fun Playable.toItem(): CompilationCreatorSound? = when (this) {
    is Compilation -> null
    is Sound -> CompilationCreatorSound(
        key = key,
        name = name,
        sound = this,
    )
}
