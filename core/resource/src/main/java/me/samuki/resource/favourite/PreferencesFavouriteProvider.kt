package me.samuki.resource.favourite

import kotlinx.coroutines.flow.MutableSharedFlow
import me.samuki.model.NoAnswer
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import javax.inject.Inject

internal class PreferencesFavouriteProvider @Inject constructor() : FavouriteProvider {

    private val cache: MutableMap<Id, LikeState> = mutableMapOf()
    override val favouriteProvider = MutableSharedFlow<Map<Id, LikeState>>(replay = 1)

    override suspend fun makeFavourite(id: Id): Result<NoAnswer> = setState(id, LikeState.Favourite)

    override suspend fun makeNormal(id: Id): Result<NoAnswer> = setState(id, LikeState.Normal)

    private suspend fun setState(id: Id, state: LikeState): Result<NoAnswer> {
        cache[id] = state
        favouriteProvider.emit(cache)
        return Result.success(NoAnswer)
    }
}
