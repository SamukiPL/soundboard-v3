package me.samuki.domain.sound.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Sound
import javax.inject.Inject

class SetSoundAsNotification @Inject constructor(
    private val setSoundRepository: SetSoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(sound: Sound) = withContext(coroutineDispatcher) {
        setSoundRepository.setAsNotification(sound)
    }
}
