package me.samuki.resource.sounds.provider

import kotlinx.coroutines.flow.Flow
import me.samuki.model.Sound
import me.samuki.model.values.Id

public interface SoundsDataSource {

    public val soundFlow: Flow<List<Sound>>

    public suspend fun makeFavourite(id: Id)
    public suspend fun makeNormal(id: Id)
}
