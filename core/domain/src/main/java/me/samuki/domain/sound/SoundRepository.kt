package me.samuki.domain.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.model.values.Query
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface SoundRepository {

    suspend fun observeSounds(): Flow<List<Playable.Sound>>

    suspend fun observeFavourites(): Flow<List<Playable.Sound>>

    suspend fun searchByQuery(query: Query): Result<NoAnswer>

    suspend fun clearSearch(): Result<NoAnswer>
}