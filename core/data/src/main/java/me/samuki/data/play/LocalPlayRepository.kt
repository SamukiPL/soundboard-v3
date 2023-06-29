package me.samuki.data.play

import me.samuki.common.rail.runNoAnswer
import me.samuki.domain.PlayRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.resource.player.Player
import javax.inject.Inject

class LocalPlayRepository @Inject constructor(
    private val player: Player
) : PlayRepository {

    override suspend fun stopPlaying(): Result<NoAnswer> = runNoAnswer {
        player.stopPlaying()
    }

    override suspend fun playSound(sound: Sound): Result<NoAnswer> = runNoAnswer {
        player.playSound(sound)
    }

    override suspend fun playCompilation(compilation: Compilation): Result<NoAnswer> = runNoAnswer {
        player.playCompilation(compilation)
    }
}
