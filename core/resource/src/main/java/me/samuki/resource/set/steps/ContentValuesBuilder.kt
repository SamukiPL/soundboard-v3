package me.samuki.resource.set.steps

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import me.samuki.model.values.AppName
import me.samuki.model.values.Name
import java.io.File
import javax.inject.Inject

internal class ContentValuesBuilder @Inject constructor(
    private val appName: AppName,
    private val sdkVersionProvider: () -> Int = { Build.VERSION.SDK_INT },
) {

    fun build(
        soundFile: File,
        combinablesName: Name
    ): ContentValues = ContentValues().apply {
        if (sdkVersionProvider() < Build.VERSION_CODES.Q)
            put(MediaStore.MediaColumns.DATA, soundFile.absolutePath)
        else
            put(MediaStore.Audio.Media.IS_PENDING, 1)
        put(MediaStore.MediaColumns.TITLE, combinablesName.value)
        put(MediaStore.MediaColumns.DISPLAY_NAME, combinablesName.value)
        put(MediaStore.MediaColumns.SIZE, soundFile.length())
        put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
        put(MediaStore.Audio.AudioColumns.ARTIST, appName.value)
        put(MediaStore.Audio.AudioColumns.IS_RINGTONE, true)
        put(MediaStore.Audio.AudioColumns.IS_NOTIFICATION, true)
        put(MediaStore.Audio.AudioColumns.IS_ALARM, true)
        put(MediaStore.Audio.AudioColumns.IS_MUSIC, false)
    }
}
