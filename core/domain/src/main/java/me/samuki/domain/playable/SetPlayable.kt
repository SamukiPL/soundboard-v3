package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.set.SetCompilation
import me.samuki.domain.params.SetType
import me.samuki.domain.sound.set.SetSound
import me.samuki.model.Compilation
import me.samuki.model.Playable
import me.samuki.model.Sound
import javax.inject.Inject

class SetPlayable @Inject constructor(
    private val setCompilation: SetCompilation,
    private val setSound: SetSound,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(playable: Playable, setType: SetType) =
        withContext(coroutineDispatcher) {
            when (playable) {
                is Compilation -> setCompilation(playable, setType)
                is Sound -> setSound(playable, setType)
            }
        }
}
