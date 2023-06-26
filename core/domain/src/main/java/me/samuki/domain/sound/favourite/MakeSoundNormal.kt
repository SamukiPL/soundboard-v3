package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Playable
import javax.inject.Inject

class MakeSoundNormal @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
){
    suspend operator fun invoke(sound: Playable.Sound) = withContext(coroutineDispatcher) {
        soundRepository.makeNormal(sound)
    }
}
