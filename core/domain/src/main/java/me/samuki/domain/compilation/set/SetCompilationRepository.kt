package me.samuki.domain.compilation.set

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer

public interface SetCompilationRepository {

    public suspend fun setAsNotification(compilation: Compilation): Result<NoAnswer>

    public suspend fun setAsRingtone(compilation: Compilation): Result<NoAnswer>
}
