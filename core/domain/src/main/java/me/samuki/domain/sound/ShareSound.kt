package me.samuki.domain.sound

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.ShareRepository
import me.samuki.model.Playable
import javax.inject.Inject

class ShareSound @Inject constructor(
    private val shareRepository: ShareRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(sound: Playable.Sound) = withContext(coroutineDispatcher) {
        shareRepository.shareSound(sound)
    }
}
