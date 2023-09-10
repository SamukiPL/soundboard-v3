package me.samuki.domain.sound.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Playable
import javax.inject.Inject

public class ObserveAllSounds @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(): Flow<List<Playable>> = withContext(coroutineDispatcher) {
        soundRepository.observeSounds()
    }
}
