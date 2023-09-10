package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class StopPlaying @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Result<NoAnswer> = withContext(coroutineDispatcher) {
        playRepository.stopPlaying()
    }
}
