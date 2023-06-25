package me.samuki.resource.provider

import kotlinx.coroutines.flow.Flow
import me.samuki.resource.model.ResourceSound

interface SoundsProvider {

    val soundFlow: Flow<List<ResourceSound>>
}
