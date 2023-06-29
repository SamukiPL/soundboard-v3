package me.samuki.domain.compilation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import me.samuki.model.Compilation
import javax.inject.Inject

class PlayCompilation @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(compilation: Compilation) =
        withContext(coroutineDispatcher) {
            playRepository.playCompilation(compilation)
        }
}
