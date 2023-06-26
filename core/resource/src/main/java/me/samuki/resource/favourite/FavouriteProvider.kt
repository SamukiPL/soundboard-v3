package me.samuki.resource.favourite

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState

internal interface FavouriteProvider {

    val favouriteProvider: Flow<Map<Id, LikeState>>

    suspend fun makeFavourite(id: Id): Result<NoAnswer>
    suspend fun makeNormal(id: Id): Result<NoAnswer>
}
