package me.samuki.feature.compilation.data

import kotlinx.coroutines.flow.MutableSharedFlow
import me.samuki.common.rail.runNoAnswer
import me.samuki.domain.compilation.creation.CreateCompilation
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.NoAnswer
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import javax.inject.Inject

internal class LocalCompilationCreationFeatureRepository @Inject constructor(
    private val createCompilation: CreateCompilation
) : CompilationCreationFeatureRepository {

    private var _list = mutableListOf<CombinedCombinable>()

    override val items: MutableSharedFlow<List<CombinedCombinable>> = MutableSharedFlow(replay = 1)

    override suspend fun addSound(sound: Sound): Result<NoAnswer> = runNoAnswer {
        _list.add(sound.toCombinable())
    }

    override suspend fun addPause(pause: Pause): Result<NoAnswer> = runNoAnswer {
        _list.add(pause.toCombinable())
    }

    override suspend fun removeCombinable(item: CombinedCombinable): Result<NoAnswer> = runNoAnswer {
        _list.clear()
        items.emit(_list)
    }

    override suspend fun endCreation(name: Name): Result<NoAnswer> = runNoAnswer {
        createCompilation(
            CreateCompilation.Params(
                name,
                _list.map { it.combinable }
            )
        )
    }
}

private fun Pause.toCombinable(): CombinedCombinable = CombinedCombinable(
    Id(1),
    this
)

private fun Sound.toCombinable(): CombinedCombinable = CombinedCombinable(
    this.id,
    this
)
