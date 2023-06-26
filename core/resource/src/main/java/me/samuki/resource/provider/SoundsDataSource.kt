package me.samuki.resource.provider

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.values.Id

interface SoundsDataSource {

    val soundFlow: Flow<List<Playable.Sound>>

    suspend fun makeFavourite(id: Id): Result<NoAnswer>
    suspend fun makeNormal(id: Id): Result<NoAnswer>
}
