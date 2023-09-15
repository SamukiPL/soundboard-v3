package me.samuki.resource.set.steps

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

internal class WriteFileToResolver @Inject constructor(
    private val contentResolver: ContentResolver,
    private val fileWriter: WriteAudioToFile
) {

    operator fun invoke(file: File, uri: Uri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            contentResolver.openFileDescriptor(uri, WRITE_MODE, null).use {
                val inputStream = file.inputStream()
                val outputStream = FileOutputStream(it!!.fileDescriptor)
                fileWriter.writeInputToOutput(inputStream, outputStream)
                inputStream.close()
                outputStream.close()
            }
            val values = ContentValues().apply {
                put(MediaStore.Audio.Media.IS_PENDING, 0)
            }
            contentResolver.update(uri, values, null, null)
        }
    }

    private companion object {
        const val WRITE_MODE = "w"
    }
}
