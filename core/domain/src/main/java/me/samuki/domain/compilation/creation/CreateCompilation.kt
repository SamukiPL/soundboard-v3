package me.samuki.domain.compilation.creation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.Sound
import me.samuki.model.values.Name
import javax.inject.Inject

class CreateCompilation @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(params: Params) = withContext(coroutineDispatcher) {
        compilationRepository.createCompilation(params.name, params.list)
    }

    data class Params(
        val name: Name,
        val list: List<Sound>
    )
}
