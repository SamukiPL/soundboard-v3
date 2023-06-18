package me.samuki.domain.sound.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.params.SetType
import me.samuki.model.Playable
import javax.inject.Inject

class SetSound @Inject constructor(
    private val setSoundAsNotification: SetSoundAsNotification,
    private val setSoundAsRingtone: SetSoundAsRingtone,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(sound: Playable.Sound, setType: SetType) =
        withContext(coroutineDispatcher) {
            when (setType) {
                SetType.Notification -> setSoundAsNotification(sound)
                SetType.Ringtone -> setSoundAsRingtone(sound)
            }
        }
}
