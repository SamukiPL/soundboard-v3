package me.samuki.resource.thankyou

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import me.samuki.resource.datastore.dataStore
import javax.inject.Inject

internal class ThankYouPreferenceProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ThankYouProvider {

    private val thankYouKey = booleanPreferencesKey("thank_you_shown")

    override suspend fun shouldShowThankYouAndMarkAsShown(): Boolean {
        var shouldShow = false
        context.dataStore.edit { preferences ->
            val alreadyShown = preferences[thankYouKey] ?: false
            if (!alreadyShown) {
                preferences[thankYouKey] = true
                shouldShow = true
            }
        }
        return shouldShow
    }
}
