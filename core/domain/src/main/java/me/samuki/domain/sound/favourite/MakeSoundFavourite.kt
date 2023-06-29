package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Sound
import javax.inject.Inject

class MakeSoundFavourite @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(sound: Sound) = withContext(coroutineDispatcher) {
        soundRepository.makeFavourite(sound)
    }
}
