@file:OptIn(InternalDomainApi::class)

package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.rail.andThen
import me.samuki.domain.compilation.set.SetCompilation
import me.samuki.domain.lint.InternalDomainApi
import me.samuki.domain.params.SetType
import me.samuki.domain.permissions.CheckWriteSettingsPermissionStateAndThrowOnRefused
import me.samuki.domain.sound.set.SetSound
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.Sound
import javax.inject.Inject

public class SetPlayable @Inject constructor(
    private val checkWriteSettingsPermissionStateAndThrowOnRefused: CheckWriteSettingsPermissionStateAndThrowOnRefused,
    private val setCompilation: SetCompilation,
    private val setSound: SetSound,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) {
    public suspend operator fun invoke(playable: Playable, setType: SetType): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            checkWriteSettingsPermissionStateAndThrowOnRefused().andThen {
                when (playable) {
                    is Compilation -> setCompilation(playable, setType)
                    is Sound -> setSound(playable, setType)
                }
            }
        }
}
