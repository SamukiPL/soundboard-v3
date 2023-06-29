package me.samuki.resource.player

import me.samuki.model.Compilation
import me.samuki.model.Sound

interface Player {

    suspend fun stopPlaying()

    suspend fun playSound(sound: Sound)

    suspend fun playCompilation(compilation: Compilation)
}
