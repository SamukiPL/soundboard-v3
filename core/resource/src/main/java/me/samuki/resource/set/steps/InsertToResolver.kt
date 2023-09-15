package me.samuki.resource.set.steps

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import javax.inject.Inject

internal class InsertToResolver @Inject constructor(
    private val contentResolver: ContentResolver,
    private val audioCollection: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
) {

    fun insert(contentValues: ContentValues): Uri {
        return requireNotNull(contentResolver.insert(audioCollection, contentValues)) {
            "Content resolver produced null Uri"
        }
    }
}
