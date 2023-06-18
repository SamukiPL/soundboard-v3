package me.samuki.domain

import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface PlayRepository {

    suspend fun playSound(sound: Playable.Sound): Result<NoAnswer>

    suspend fun playCompilation(compilation: Playable.Compilation): Result<NoAnswer>
}
