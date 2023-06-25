package me.samuki.data.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.common.ext.mapList
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.values.Query
import me.samuki.resource.provider.SoundsProvider
import javax.inject.Inject

internal class LocalSoundRepository @Inject constructor(
    private val soundProvider: SoundsProvider
) : SoundRepository {
    override suspend fun observeSounds(): Flow<List<Playable.Sound>> = soundProvider.soundFlow
        .mapList {
            Playable.Sound(
                id = it.id,
                isFavourite = false
            )
        }

    override suspend fun observeFavourites(): Flow<List<Playable.Sound>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByQuery(query: Query): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun clearSearch(): Result<NoAnswer> {
        TODO("Not yet implemented")
    }
}
