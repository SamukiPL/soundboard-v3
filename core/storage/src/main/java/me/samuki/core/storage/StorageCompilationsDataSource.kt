package me.samuki.core.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import me.samuki.common.algorithm.linkedSort
import me.samuki.core.storage.dao.CompilationsDao
import me.samuki.core.storage.dao.PausesDao
import me.samuki.core.storage.dao.SoundsDao
import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity
import me.samuki.core.storage.mapper.combinable.toSortable
import me.samuki.core.storage.mapper.toEntities
import me.samuki.core.storage.mapper.toStorage
import me.samuki.core.storage.model.StorageCombinable
import me.samuki.core.storage.model.StorageCompilation
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import javax.inject.Inject

internal class StorageCompilationsDataSource @Inject constructor(
    private val compilationsDao: CompilationsDao,
    private val pausesDao: PausesDao,
    private val soundsDao: SoundsDao,
) : CompilationsDataSource {
    override val compilationsFlow: Flow<List<StorageCompilation>>
        get() = combine(
            compilationsDao.getCompilations(),
            pausesDao.getAllPauses(),
            soundsDao.getAllSounds()
        ) { compilations, pauses, sounds ->
            val combinablesMap =
                (pauses.map(PauseEntity::toSortable) + sounds.map(SoundEntity::toSortable))
                    .groupBy { it.compilationId }
                    .mapValues { entry ->
                        entry.value.linkedSort(
                            key = { item -> item.id.value },
                            nextKey = { item -> item.nextCombinableId?.value }
                        ).map { item ->
                            item.storageCombinable
                        }
                    }
            compilations.map {
                it.toStorage(combinablesMap.getOrDefault(it.id, emptyList()))
            }
        }

    override fun addCompilation(name: Name, combinables: List<StorageCombinable>) {
        val compilationId = compilationsDao.insertCompilation(name).toId()
        combinables.toEntities(compilationId).run {
            pausesDao.savePauses(pauses)
            soundsDao.saveSounds(sounds)
        }
    }

    override fun deleteCompilation(id: Id) {
        compilationsDao.deleteCompilation(id)
        pausesDao.deletePauses(id)
        soundsDao.deleteSounds(id)
    }
}

private fun Long.toId(): Id = Id(value = this.toInt())
