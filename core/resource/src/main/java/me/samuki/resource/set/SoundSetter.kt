package me.samuki.resource.set

import me.samuki.model.Sound

public interface SoundSetter {

    public suspend fun setAsNotification(sound: Sound)

    public suspend fun setAsRingtone(sound: Sound)
}
