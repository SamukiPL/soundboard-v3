package me.samuki.resource.set

import android.media.RingtoneManager
import me.samuki.model.Sound
import me.samuki.model.values.Name
import javax.inject.Inject

internal class SoundSettingsSetter @Inject constructor(
    setterDelegate: SetterDelegate
) : SoundSetter, Setter by setterDelegate {

    override suspend fun setAsNotification(sound: Sound) {
        setCombinables(
            sound.idAsName(),
            RingtoneManager.TYPE_NOTIFICATION,
            sound
        )
    }

    override suspend fun setAsRingtone(sound: Sound) {
        setCombinables(
            sound.idAsName(),
            RingtoneManager.TYPE_RINGTONE,
            sound
        )
    }

    private fun Sound.idAsName() = Name(id.value.toString())
}
