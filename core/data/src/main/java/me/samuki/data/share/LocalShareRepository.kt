package me.samuki.data.share

import me.samuki.common.rail.runNoAnswer
import me.samuki.domain.ShareRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.resource.share.ShareHub
import javax.inject.Inject

internal class LocalShareRepository @Inject constructor(
    private val shareHub: ShareHub
) : ShareRepository {

    override suspend fun shareSound(sound: Sound): Result<NoAnswer> = runNoAnswer {
        shareHub.shareCombinables(sound.name, sound)
    }

    override suspend fun shareCompilation(compilation: Compilation): Result<NoAnswer> =
        runNoAnswer {
            shareHub.shareCombinables(compilation.name, *compilation.sounds.toTypedArray())
        }
}
