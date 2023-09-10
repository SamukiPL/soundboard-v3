package me.samuki.data.set

import me.samuki.domain.compilation.set.SetCompilationRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.resource.set.CompilationSetter

internal class LocalSetCompilationRepository(
    private val compilationSetter: CompilationSetter
) : SetCompilationRepository {
    override suspend fun setAsNotification(compilation: Compilation): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun setAsRingtone(compilation: Compilation): Result<NoAnswer> {
        TODO("Not yet implemented")
    }
}
