package me.samuki.resource.file

import android.content.ContentResolver
import me.samuki.model.values.Name
import me.samuki.model.values.Path
import me.samuki.resource.di.FilesDir
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

internal class WriteAudioToFile @Inject constructor(
    @FilesDir private val externalFile: File,
    private val contentResolver: ContentResolver,
) {

    fun writeCombinables(fileName: Name, audioPaths: List<Path>): File =
        createFile(fileName).apply {
            outputStream().use { outputStream ->
                audioPaths.forEach { path ->
                    writeFromPathToOutput(path, outputStream)
                }
            }
        }

    private fun createFile(combinablesName: Name): File = File(
        externalFile,
        "${combinablesName.value}.mp3"
    ).also {
        it.parentFile?.mkdirs()
        it.createNewFile()
    }

    private fun writeFromPathToOutput(path: Path, outputStream: FileOutputStream) {
        contentResolver.openAssetFileDescriptor(path.value, READ_MODE)?.use { fileDescriptor ->
            fileDescriptor.createInputStream().use { inputStream ->
                writeInputToOutput(inputStream, outputStream)
            }
        }
    }

    fun writeInputToOutput(inputStream: FileInputStream, outputStream: FileOutputStream) {
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
