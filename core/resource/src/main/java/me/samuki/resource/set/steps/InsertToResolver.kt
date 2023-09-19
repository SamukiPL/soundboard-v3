package me.samuki.resource.set.steps

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import me.samuki.resource.di.ExternalContentUri
import javax.inject.Inject

internal class InsertToResolver @Inject constructor(
    private val contentResolver: ContentResolver,
    @ExternalContentUri private val audioCollection: Uri
) {

    fun insert(contentValues: ContentValues): Uri {
        return requireNotNull(contentResolver.insert(audioCollection, contentValues)) {
            "Content resolver produced null Uri"
        }
    }
}
