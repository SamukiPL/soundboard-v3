package me.samuki.resource.player

import me.samuki.model.Compilation
import me.samuki.model.Sound

public interface Player {

    public suspend fun stopPlaying()

    public suspend fun playSound(sound: Sound)

    public suspend fun playCompilation(compilation: Compilation)
}
