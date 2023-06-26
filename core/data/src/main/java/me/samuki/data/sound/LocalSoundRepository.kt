package me.samuki.data.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.values.Query
import me.samuki.resource.provider.SoundsDataSource
import javax.inject.Inject

internal class LocalSoundRepository @Inject constructor(
    private val soundProvider: SoundsDataSource
) : SoundRepository {
    override suspend fun observeSounds(): Flow<List<Playable.Sound>> = soundProvider.soundFlow

    override suspend fun searchByQuery(query: Query): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun clearSearch(): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun makeFavourite(sound: Playable.Sound): Result<NoAnswer> =
        soundProvider.makeFavourite(sound.id)

    override suspend fun makeNormal(sound: Playable.Sound): Result<NoAnswer> =
        soundProvider.makeNormal(sound.id)
}
