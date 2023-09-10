package me.samuki.resource.set.files

import android.content.ContentResolver
import androidx.core.net.toFile
import me.samuki.model.values.Path
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

internal interface SaveAudioToFile {

}

internal class SaveAudioToFileImpl @Inject constructor(
    private val contentResolver: ContentResolver
) : SaveAudioToFile {

    operator fun invoke(filePath: Path, audioPaths: List<Path>): File =
        filePath.value.toFile().apply {
            outputStream().use { outputStream ->
                audioPaths.forEach { path ->
                    writeFromPathToOutput(path, outputStream)
                }
            }
        }

    private fun writeFromPathToOutput(path: Path, outputStream: FileOutputStream) {
        contentResolver.openAssetFileDescriptor(path.value, READ_MODE)?.use { fileDescriptor ->
            fileDescriptor.createInputStream().use { inputStream ->
                writeToOutput(inputStream, outputStream)
            }
        }
    }

    private fun writeToOutput(inputStream: FileInputStream, outputStream: FileOutputStream) {
        val bArr = ByteArray(BUFFER_SIZE)
        var read = inputStream.read(bArr)
        while (read != -1) {
            outputStream.write(bArr, 0, read)
            read = inputStream.read(bArr)
        }
    }

    private companion object {
        const val READ_MODE = "r"
        const val BUFFER_SIZE = 1024
    }
}
