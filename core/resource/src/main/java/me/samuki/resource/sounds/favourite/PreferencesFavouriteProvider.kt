package me.samuki.resource.sounds.favourite

import kotlinx.coroutines.flow.MutableStateFlow
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import javax.inject.Inject

internal class PreferencesFavouriteProvider @Inject constructor() : FavouriteProvider {

    private val cache: MutableMap<Id, LikeState> = mutableMapOf()
    override val favouriteProvider = MutableStateFlow<Map<Id, LikeState>>(emptyMap())

    override suspend fun makeFavourite(id: Id) = setState(id, LikeState.Favourite)

    override suspend fun makeNormal(id: Id) = setState(id, LikeState.Normal)

    private suspend fun setState(id: Id, state: LikeState) {
        cache[id] = state
        favouriteProvider.emit(cache)
    }
}
