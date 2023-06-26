package me.samuki.domain.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface SoundRepository {

    suspend fun observeSounds(): Flow<List<Playable.Sound>>

    suspend fun makeFavourite(sound: Playable.Sound): Result<NoAnswer>

    suspend fun makeNormal(sound: Playable.Sound): Result<NoAnswer>
}
