package me.samuki.resource.set

import me.samuki.model.Compilation

public interface CompilationSetter {

    public fun setAsNotification(compilation: Compilation)

    public fun setAsRingtone(compilation: Compilation)
}
