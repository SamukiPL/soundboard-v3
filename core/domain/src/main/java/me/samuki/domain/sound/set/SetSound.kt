package me.samuki.domain.sound.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.lint.InternalDomainApi
import me.samuki.domain.params.SetType
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import javax.inject.Inject

@InternalDomainApi
public class SetSound @Inject constructor(
    private val setSoundAsNotification: SetSoundAsNotification,
    private val setSoundAsRingtone: SetSoundAsRingtone,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(sound: Sound, setType: SetType): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (setType) {
                SetType.Notification -> setSoundAsNotification(sound)
                SetType.Ringtone -> setSoundAsRingtone(sound)
            }
        }
}
