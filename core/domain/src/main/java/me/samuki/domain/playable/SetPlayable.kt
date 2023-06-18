package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.set.SetCompilation
import me.samuki.domain.params.SetType
import me.samuki.domain.sound.set.SetSound
import me.samuki.model.Playable
import javax.inject.Inject

class SetPlayable @Inject constructor(
    private val setCompilation: SetCompilation,
    private val setSound: SetSound,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(playable: Playable, setType: SetType) =
        withContext(coroutineDispatcher) {
            when (playable) {
                is Playable.Compilation -> setCompilation(playable, setType)
                is Playable.Sound -> setSound(playable, setType)
            }
        }
}
