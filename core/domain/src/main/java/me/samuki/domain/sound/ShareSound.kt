package me.samuki.domain.sound

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.ShareRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import javax.inject.Inject

public class ShareSound @Inject constructor(
    private val shareRepository: ShareRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(sound: Sound): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            shareRepository.shareSound(sound)
        }
}
