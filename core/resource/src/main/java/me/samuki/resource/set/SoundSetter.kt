package me.samuki.resource.set

import me.samuki.model.Sound

public interface SoundSetter {

    public fun setAsNotification(sound: Sound)

    public fun setAsRingtone(sound: Sound)
}
