package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.PlayCompilation
import me.samuki.domain.sound.PlaySound
import me.samuki.model.Compilation
import me.samuki.model.Playable
import me.samuki.model.Sound
import javax.inject.Inject

class PlayPlayable @Inject constructor(
    private val playCompilation: PlayCompilation,
    private val playSound: PlaySound,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(playable: Playable) = withContext(coroutineDispatcher) {
        when (playable) {
            is Compilation -> playCompilation(playable)
            is Sound -> playSound(playable)
        }
    }
}
