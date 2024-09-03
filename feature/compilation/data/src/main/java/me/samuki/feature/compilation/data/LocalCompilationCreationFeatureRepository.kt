package me.samuki.feature.compilation.data

import kotlinx.coroutines.flow.MutableSharedFlow
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

internal class LocalCompilationCreationFeatureRepository @Inject constructor(
    private val createCompilation: CreateCompilation,
    private val combinedTemporaryIdDataSource: CombinedTemporaryIdDataSource,
) : CompilationCreationFeatureRepository {

    private var _list = mutableListOf<CombinedCombinable>()

    override val items: MutableSharedFlow<List<CombinedCombinable>> = MutableSharedFlow(replay = 1)

    override suspend fun addSound(sound: Sound): Result<NoAnswer> = runNoAnswer {
        _list.add(
            sound.toCombinable(
                id = combinedTemporaryIdDataSource.generateId()
            )
        )
    }

    override suspend fun addPause(pause: Pause): Result<NoAnswer> = runNoAnswer {
        _list.add(
            pause.toCombinable(
                id = combinedTemporaryIdDataSource.generateId()
            )
        )
    }

    override suspend fun removeCombinable(item: CombinedCombinable): Result<NoAnswer> =
        runNoAnswer {
            _list.clear()
            items.emit(_list)
        }

    override suspend fun endCreation(name: Name): Result<NoAnswer> = runNoAnswer {
        createCompilation(
            CreateCompilation.Params(
                name,
                _list.combinables
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
