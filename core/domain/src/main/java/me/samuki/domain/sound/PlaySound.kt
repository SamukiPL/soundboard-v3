package me.samuki.domain.sound

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import javax.inject.Inject

public class PlaySound @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(sound: Sound): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            playRepository.playSound(sound)
        }
}
