package me.samuki.domain.compilation.set

import me.samuki.model.NoAnswer
import me.samuki.model.Playable

interface SetCompilationRepository {

    suspend fun setAsNotification(compilation: Playable.Compilation): Result<NoAnswer>

    suspend fun setAsRingtone(compilation: Playable.Compilation): Result<NoAnswer>
}
