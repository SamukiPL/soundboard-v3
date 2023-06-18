package me.samuki.domain

import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface ShareRepository {

    suspend fun shareSound(sound: Playable.Sound): Result<NoAnswer>

    suspend fun shareCompilation(compilation: Playable.Compilation): Result<NoAnswer>
}
