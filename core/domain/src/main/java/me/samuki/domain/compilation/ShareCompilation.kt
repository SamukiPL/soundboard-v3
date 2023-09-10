package me.samuki.domain.compilation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.ShareRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class ShareCompilation @Inject constructor(
    private val shareRepository: ShareRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(compilation: Compilation): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            shareRepository.shareCompilation(compilation)
        }
}
