package me.samuki.domain.compilation.creation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import javax.inject.Inject

public class EditCompilation @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(params: Params): Result<NoAnswer> = withContext(coroutineDispatcher) {
        compilationRepository.editCompilation(params.id, params.name, params.list)
    }

    public data class Params(
        val id: Id,
        val name: Name,
        val list: List<Sound>
    )
}
