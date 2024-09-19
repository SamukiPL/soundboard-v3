package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.rail.runCatchingWithContext
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.mapper.combinables
import me.samuki.model.Compilation
import me.samuki.model.util.emptyName
import me.samuki.model.util.emptySupplement
import me.samuki.model.util.temporaryId
import javax.inject.Inject

public class PrepareTemporaryCompilation @Inject constructor(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Result<Compilation> =
        runCatchingWithContext(coroutineDispatcher) {
            val combinables = repository.items.value.combinables

            if (combinables.isEmpty()) {
                throw IllegalStateException(EMPTY_LIST_MESSAGE)
            }
            Compilation(
                id = temporaryId(),
                supplement = emptySupplement(),
                name = emptyName(),
                sounds = combinables,
            )
        }.onFailure {
            println("XYZ $it")
        }

    private companion object {
        const val EMPTY_LIST_MESSAGE = "The list of combinables is empty"
    }
}
