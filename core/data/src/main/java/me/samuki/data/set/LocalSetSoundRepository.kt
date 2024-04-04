package me.samuki.data.set

import me.samuki.domain.rail.runNoAnswer
import me.samuki.domain.sound.set.SetSoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.resource.set.SoundSetter
import javax.inject.Inject

internal class LocalSetSoundRepository @Inject constructor(
    private val soundSetter: SoundSetter
) : SetSoundRepository {

    override suspend fun setAsNotification(sound: Sound): Result<NoAnswer> = runNoAnswer {
        soundSetter.setAsNotification(sound)
    }

    override suspend fun setAsRingtone(sound: Sound): Result<NoAnswer> = runNoAnswer {
        soundSetter.setAsRingtone(sound)
    }
}
