package me.samuki.resource.provider

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.resource.favourite.FavouriteProvider
import me.samuki.resource.reader.ResourceRawReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ResourceSoundsDataSource @Inject constructor(
    private val resourceRawReader: ResourceRawReader,
    private val favouriteProvider: FavouriteProvider
) : SoundsDataSource {

    override val soundFlow: Flow<List<Playable.Sound>>
        get() = resourceRawReader.resourceRawFlow.combine(favouriteProvider.favouriteProvider) { raws, favourites ->
            raws.map {
                Playable.Sound(
                    id = it.id,
                    name = it.name,
                    path = it.path,
                    likeState = favourites.getOrDefault(it.id, LikeState.Normal)
                )
            }
        }

    override suspend fun makeFavourite(id: Id): Result<NoAnswer> =
        favouriteProvider.makeFavourite(id)

    override suspend fun makeNormal(id: Id): Result<NoAnswer> =
        favouriteProvider.makeNormal(id)
}
