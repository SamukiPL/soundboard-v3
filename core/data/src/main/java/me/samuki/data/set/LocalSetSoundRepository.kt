package me.samuki.data.set

import me.samuki.domain.sound.set.SetSoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.resource.set.SoundSetter

internal class LocalSetSoundRepository(
    private val soundSetter: SoundSetter
) : SetSoundRepository {
    override suspend fun setAsNotification(sound: Sound): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun setAsRingtone(sound: Sound): Result<NoAnswer> {
        TODO("Not yet implemented")
    }
}
