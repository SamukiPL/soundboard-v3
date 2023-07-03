package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import javax.inject.Inject

class StopPlaying @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
){

    suspend operator fun invoke() = withContext(coroutineDispatcher) {
        playRepository.stopPlaying()
    }
}
