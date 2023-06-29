package me.samuki.domain.sound.set

import me.samuki.model.NoAnswer
import me.samuki.model.Sound

interface SetSoundRepository {

    suspend fun setAsNotification(sound: Sound): Result<NoAnswer>

    suspend fun setAsRingtone(sound: Sound): Result<NoAnswer>
}
