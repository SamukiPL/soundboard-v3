package me.samuki.core.storage.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import me.samuki.common.di.DispatcherIO
import me.samuki.core.storage.di.PauseQueries
import me.samuki.core.storage.entity.PauseEntity
import me.samuki.model.values.Id
import javax.inject.Inject

internal class PausesDao @Inject constructor(
    private val pauseQueries: PauseQueries,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getAllPauses(): Flow<List<PauseEntity>> = pauseQueries.selectAll()
        .asFlow()
        .mapToList(coroutineDispatcher)

    fun savePauses(entities: List<PauseEntity>) {
        pauseQueries.transaction {
            entities.forEach { entity ->
                pauseQueries.insertPause(entity)
            }
        }
    }

    fun deletePauses(id: Id) {
        pauseQueries.deleteAllForCompilation(id)
    }
}
