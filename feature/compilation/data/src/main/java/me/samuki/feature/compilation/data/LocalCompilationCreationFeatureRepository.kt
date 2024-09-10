package me.samuki.feature.compilation.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import me.samuki.domain.compilation.creation.CreateCompilation
import me.samuki.domain.rail.runNoAnswer
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.mapper.combinables
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.NoAnswer
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalCompilationCreationFeatureRepository @Inject constructor(
    private val createCompilation: CreateCompilation,
    private val combinedTemporaryIdDataSource: CombinedTemporaryIdDataSource,
) : CompilationCreationFeatureRepository {

    override val items: MutableStateFlow<List<CombinedCombinable>> = MutableStateFlow(emptyList())

    override suspend fun addSound(sound: Sound): Result<NoAnswer> = runNoAnswer {
        items.update { list ->
            list + sound.toCombinable(
                id = combinedTemporaryIdDataSource.generateId()
            )
        }
    }

    override suspend fun addPause(pause: Pause): Result<NoAnswer> = runNoAnswer {
        items.update { list ->
            list + pause.toCombinable(
                id = combinedTemporaryIdDataSource.generateId()
            )
        }
    }

    override suspend fun removeCombinable(item: CombinedCombinable): Result<NoAnswer> =
        runNoAnswer {
            items.update { list ->
                list.toMutableList().apply {
                    remove(item)
                }
            }
        }

    override suspend fun endCreation(name: Name): Result<NoAnswer> = runNoAnswer {
        createCompilation(
            CreateCompilation.Params(
                name,
                items.value.combinables
            )
        )
    }
}

private fun Pause.toCombinable(id: Id): CombinedCombinable = CombinedCombinable(
    id,
    this
)

private fun Sound.toCombinable(id: Id): CombinedCombinable = CombinedCombinable(
    id,
    this
)
