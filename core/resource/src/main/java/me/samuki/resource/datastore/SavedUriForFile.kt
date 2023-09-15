package me.samuki.resource.datastore

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import me.samuki.model.values.Name
import javax.inject.Inject

internal class SavedUriForFile @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    suspend fun getUri(fileName: Name): Uri? = context.dataStore.data.firstOrNull()
        ?.get(fileName.asPreferenceKey())
        ?.toUri()

    suspend fun saveUri(fileName: Name, uri: Uri) {
        context.dataStore.edit {
            it[fileName.asPreferenceKey()] = uri.toString()
        }
    }

    private fun Name.asPreferenceKey() = stringPreferencesKey(WAS_URI_SAVED + this.value)

    private companion object {
        const val WAS_URI_SAVED = "was_uri_saved"
    }
}
