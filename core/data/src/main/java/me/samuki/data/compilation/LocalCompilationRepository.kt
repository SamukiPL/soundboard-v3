package me.samuki.data.compilation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.samuki.common.rail.runNoAnswer
import me.samuki.common.util.EMPTY_STRING
import me.samuki.core.storage.CompilationsDataSource
import me.samuki.core.storage.model.StoragePause
import me.samuki.core.storage.model.StorageSound
import me.samuki.data.compilation.mapper.toStorage
import me.samuki.data.di.DataCoroutinesScope
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Combinable
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Pause
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Supplement
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalCompilationRepository @Inject constructor(
    private val compilationsDataSource: CompilationsDataSource,
    private val soundsRepository: SoundRepository,
    @DataCoroutinesScope private val coroutinesScope: CoroutineScope
) : CompilationRepository {

    private var statedFlow: Flow<List<Compilation>>? = null

    override suspend fun observeCompilations(): Flow<List<Compilation>> =
        statedFlow ?: soundsRepository.observeSounds().map { sounds ->
            sounds.associateBy { sound -> sound.id to sound.supplement }
        }.combine(compilationsDataSource.compilationsFlow) { soundsMap, compilations ->
            compilations.map { compilation ->
                Compilation(
                    id = compilation.id,
                    supplement = Supplement(EMPTY_STRING),
                    name = compilation.name,
                    sounds = compilation.combinables.mapNotNull { combinable ->
                        when (combinable) {
                            is StoragePause -> Pause(combinable.repeats.toInt())
                            is StorageSound -> soundsMap[combinable.resourceId to combinable.supplement]
                        }
                    }
                )
            }
        }.also {
            statedFlow = it.stateIn(coroutinesScope) // TODO learn more about coroutines
        }

    override suspend fun createCompilation(name: Name, list: List<Combinable>): Result<NoAnswer> =
        runNoAnswer {
            compilationsDataSource.addCompilation(
                name = name,
                combinables = list.toStorage()
            )
        }

    override suspend fun deleteCompilation(id: Id): Result<NoAnswer> = runNoAnswer {
        compilationsDataSource.deleteCompilation(id)
    }
}
