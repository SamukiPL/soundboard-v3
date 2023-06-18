package me.samuki.domain.compilation.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.values.Query
import javax.inject.Inject

class SearchCompilationsByQuery @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(query: Query) = withContext(coroutineDispatcher) {
        compilationRepository.searchByQuery(query)
    }
}
