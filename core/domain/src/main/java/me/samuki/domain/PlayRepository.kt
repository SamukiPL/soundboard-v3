package me.samuki.domain

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

interface PlayRepository {

    suspend fun stopPlaying(): Result<NoAnswer>

    suspend fun playSound(sound: Sound): Result<NoAnswer>

    suspend fun playCompilation(compilation: Compilation): Result<NoAnswer>
}
