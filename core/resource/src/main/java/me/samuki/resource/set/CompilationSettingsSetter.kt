package me.samuki.resource.set

import android.media.RingtoneManager
import me.samuki.model.Compilation
import javax.inject.Inject

internal class CompilationSettingsSetter @Inject constructor(
    setterDelegate: SetterDelegate
) : CompilationSetter, Setter by setterDelegate {
    override suspend fun setAsNotification(compilation: Compilation) {
        setCombinables(
            compilation.name,
            RingtoneManager.TYPE_NOTIFICATION,
            *compilation.sounds.toTypedArray()
        )
    }

    override suspend fun setAsRingtone(compilation: Compilation) {
        setCombinables(
            compilation.name,
            RingtoneManager.TYPE_NOTIFICATION,
            *compilation.sounds.toTypedArray()
        )
    }
}
