package me.samuki.domain.sound.set

import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface SetSoundRepository {

    suspend fun setAsNotification(sound: Playable.Sound): Result<NoAnswer>

    suspend fun setAsRingtone(sound: Playable.Sound): Result<NoAnswer>
}
