package me.samuki.domain.sound.set

import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface SetSoundRepository {

    public suspend fun setAsNotification(sound: Sound): Result<NoAnswer>

    public suspend fun setAsRingtone(sound: Sound): Result<NoAnswer>
}
