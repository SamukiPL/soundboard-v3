package me.samuki.domain.compilation.set

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer

interface SetCompilationRepository {

    suspend fun setAsNotification(compilation: Compilation): Result<NoAnswer>

    suspend fun setAsRingtone(compilation: Compilation): Result<NoAnswer>
}
