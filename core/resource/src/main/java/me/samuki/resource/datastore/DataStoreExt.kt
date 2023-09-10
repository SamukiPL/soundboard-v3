package me.samuki.resource.datastore

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore


private const val DATA_STORE_NAME = "SOUNDBOARD_DATA_STORE"
private const val SHARED_PREFS_NAME = "samuki.me.soundboard.activities.main.MainActivity"

internal val Context.dataStore by preferencesDataStore(
    name = DATA_STORE_NAME,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context = context,
                sharedPreferencesName = SHARED_PREFS_NAME,
            )
        )
    }
)
