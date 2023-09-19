package me.samuki.data.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.common.rail.runNoAnswer
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.resource.sounds.provider.SoundsDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalSoundRepository @Inject constructor(
    private val soundProvider: SoundsDataSource
) : SoundRepository {
    override suspend fun observeSounds(): Flow<List<Sound>> = soundProvider.soundFlow

    override suspend fun makeFavourite(sound: Sound): Result<NoAnswer> = runNoAnswer {
        soundProvider.makeFavourite(sound.id)
    }

    override suspend fun makeNormal(sound: Sound): Result<NoAnswer> = runNoAnswer {
        soundProvider.makeNormal(sound.id)
    }
}
