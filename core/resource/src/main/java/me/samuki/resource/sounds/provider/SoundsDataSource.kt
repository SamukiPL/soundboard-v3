package me.samuki.resource.sounds.provider

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import me.samuki.model.Sound
import me.samuki.model.values.Id

interface SoundsDataSource {

    val soundFlow: Flow<List<Sound>>

    suspend fun makeFavourite(id: Id)
    suspend fun makeNormal(id: Id)
}
