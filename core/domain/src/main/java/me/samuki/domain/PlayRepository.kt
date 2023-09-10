package me.samuki.domain

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface PlayRepository {

    public suspend fun stopPlaying(): Result<NoAnswer>

    public suspend fun playSound(sound: Sound): Result<NoAnswer>

    public suspend fun playCompilation(compilation: Compilation): Result<NoAnswer>
}
