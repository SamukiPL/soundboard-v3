package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.single
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.rail.runCatchingWithContext
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.mapper.combinables
import me.samuki.model.Compilation
import me.samuki.model.util.emptyName
import me.samuki.model.util.emptySupplement
import me.samuki.model.util.temporaryId

public class PrepareTemporaryCompilation(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Result<Compilation> =
        runCatchingWithContext(coroutineDispatcher) {
            val combinables = repository.items.single().combinables

            if (combinables.isEmpty()) {
                throw IllegalStateException(EMPTY_LIST_MESSAGE)
            }
            Compilation(
                id = temporaryId(),
                supplement = emptySupplement(),
                name = emptyName(),
                sounds = combinables,
            )
        }

    private companion object {
        const val EMPTY_LIST_MESSAGE = "The list of combinables is empty"
    }
}
