package me.samuki.domain.playable

import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.PlayCompilation
import me.samuki.domain.sound.PlaySound
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.Sound
import javax.inject.Inject

public class PlayPlayable @Inject constructor(
    private val stopPlaying: StopPlaying,
    private val playCompilation: PlayCompilation,
    private val playSound: PlaySound,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(playable: Playable): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            stopPlaying()
            when (playable) {
                is Compilation -> playCompilation(playable)
                is Sound -> playSound(playable)
            }
        }
}
