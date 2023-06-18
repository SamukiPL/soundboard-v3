package me.samuki.domain.sound

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import me.samuki.model.Playable
import javax.inject.Inject

class PlaySound @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(sound: Playable.Sound) = withContext(coroutineDispatcher) {
        playRepository.playSound(sound)
    }
}
