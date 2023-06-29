package me.samuki.resource.sounds.favourite

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState

internal interface FavouriteProvider {

    val favouriteProvider: Flow<Map<Id, LikeState>>

    suspend fun makeFavourite(id: Id)
    suspend fun makeNormal(id: Id)
}
