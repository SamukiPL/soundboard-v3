package me.samuki.resource.sounds.favourite

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.resource.datastore.dataStore
import javax.inject.Inject

internal class PreferencesFavouriteProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : FavouriteProvider {

    override val favouriteProvider: Flow<Map<Id, LikeState>> =
        context.dataStore.data.map { preferences ->
            preferences
                .asMap()
                .filter { entry ->
                    entry.key.name.contains(FAVOURITE_SOUND_PREFIX)
                }
                .map {
                    Id(
                        it.key.name.removePrefix(FAVOURITE_SOUND_PREFIX).toInt()
                    ) to (it.value as Boolean).toLikeState
                }
                .associate { it }
        }

    override suspend fun makeFavourite(id: Id) = setState(id, LikeState.Favourite)

    override suspend fun makeNormal(id: Id) = setState(id, LikeState.Normal)

    private suspend fun setState(id: Id, state: LikeState) {
        context.dataStore.edit {
            it[booleanPreferencesKey(FAVOURITE_SOUND_PREFIX + id.value)] = state.toBoolean
        }
    }

    private val Boolean.toLikeState
        get() = if (this) {
            LikeState.Favourite
        } else {
            LikeState.Normal
        }

    private val LikeState.toBoolean
        get() = when (this) {
            LikeState.Favourite -> true
            LikeState.Normal -> false
        }

    private companion object {
        const val FAVOURITE_SOUND_PREFIX = "sound"
    }
}
