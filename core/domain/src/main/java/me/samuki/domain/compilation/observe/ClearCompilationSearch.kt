package me.samuki.domain.compilation.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import javax.inject.Inject

class ClearCompilationSearch @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke() = withContext(coroutineDispatcher) {
        compilationRepository.clearSearch()
    }
}
