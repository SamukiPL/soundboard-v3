package me.samuki.resource.set

import me.samuki.model.Compilation

public interface CompilationSetter {

    public suspend fun setAsNotification(compilation: Compilation)

    public suspend fun setAsRingtone(compilation: Compilation)
}
