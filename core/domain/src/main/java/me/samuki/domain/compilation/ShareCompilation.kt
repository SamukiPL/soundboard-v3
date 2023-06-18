package me.samuki.domain.compilation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.ShareRepository
import me.samuki.model.Playable
import javax.inject.Inject

class ShareCompilation @Inject constructor(
    private val shareRepository: ShareRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(compilation: Playable.Compilation) =
        withContext(coroutineDispatcher) {
            shareRepository.shareCompilation(compilation)
        }
}
