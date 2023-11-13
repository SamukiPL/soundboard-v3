package me.samuki.core.storage.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import me.samuki.common.di.DispatcherIO
import me.samuki.core.storage.di.SoundQueries
import me.samuki.core.storage.entity.SoundEntity
import me.samuki.model.values.Id
import javax.inject.Inject

internal class SoundsDao @Inject constructor(
    private val soundQueries: SoundQueries,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getAllSounds(): Flow<List<SoundEntity>> = soundQueries.selectAll()
        .asFlow()
        .mapToList(coroutineDispatcher)

    fun saveSounds(entities: List<SoundEntity>) {
        soundQueries.transaction {
            entities.forEach { entity ->
                soundQueries.insertSound(entity)
            }
        }
    }

    fun deleteSounds(id: Id) {
        soundQueries.deleteAllForCompilation(id)
    }
}
