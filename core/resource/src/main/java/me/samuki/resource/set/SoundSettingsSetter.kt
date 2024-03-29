package me.samuki.resource.set

import android.media.RingtoneManager
import me.samuki.model.Sound
import javax.inject.Inject

internal class SoundSettingsSetter @Inject constructor(
    setterDelegate: SetterDelegate
) : SoundSetter, Setter by setterDelegate {

    override suspend fun setAsNotification(sound: Sound) {
        setCombinables(
            sound.name,
            RingtoneManager.TYPE_NOTIFICATION,
            sound
        )
    }

    override suspend fun setAsRingtone(sound: Sound) {
        setCombinables(
            sound.name,
            RingtoneManager.TYPE_RINGTONE,
            sound
        )
    }
}
