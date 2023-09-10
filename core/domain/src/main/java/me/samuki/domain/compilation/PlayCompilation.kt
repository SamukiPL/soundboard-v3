package me.samuki.domain.compilation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.PlayRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class PlayCompilation @Inject constructor(
    private val playRepository: PlayRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(compilation: Compilation): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            playRepository.playCompilation(compilation)
        }
}
