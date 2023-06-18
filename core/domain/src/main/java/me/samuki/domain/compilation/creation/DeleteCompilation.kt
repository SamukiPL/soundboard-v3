package me.samuki.domain.compilation.creation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.values.Id
import javax.inject.Inject

class DeleteCompilation @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
){

    suspend operator fun invoke(id: Id) = withContext(coroutineDispatcher) {
        compilationRepository.deleteCompilation(id)
    }
}
