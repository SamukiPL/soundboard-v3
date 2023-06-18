package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.ShareCompilation
import me.samuki.domain.sound.ShareSound
import me.samuki.model.Playable
import javax.inject.Inject

class SharePlayable @Inject constructor(
    private val shareSound: ShareSound,
    private val shareCompilation: ShareCompilation,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(playable: Playable) = withContext(coroutineDispatcher) {
        when (playable) {
            is Playable.Compilation -> shareCompilation(playable)
            is Playable.Sound -> shareSound(playable)
        }
    }
}
