package me.samuki.domain.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

interface SoundRepository {

    suspend fun observeSounds(): Flow<List<Sound>>

    suspend fun makeFavourite(sound: Sound): Result<NoAnswer>

    suspend fun makeNormal(sound: Sound): Result<NoAnswer>
}
