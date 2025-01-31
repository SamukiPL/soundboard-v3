package me.samuki.data.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.domain.rail.runNoAnswer
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Likeable
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

    override suspend fun makeFavourite(likeable: Likeable): Result<NoAnswer> = runNoAnswer {
        when (likeable) {
            is Sound -> soundProvider.makeFavourite(likeable.id)
        }
    }

    override suspend fun makeNormal(likeable: Likeable): Result<NoAnswer> = runNoAnswer {
        when (likeable) {
            is Sound -> soundProvider.makeNormal(likeable.id)
        }
    }
}
