package me.samuki.domain

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

interface ShareRepository {

    suspend fun shareSound(sound: Sound): Result<NoAnswer>

    suspend fun shareCompilation(compilation: Compilation): Result<NoAnswer>
}
