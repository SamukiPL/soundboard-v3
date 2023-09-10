package me.samuki.domain.compilation.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.Playable
import javax.inject.Inject

public class ObserveAllCompilations @Inject constructor(
    private val compilationRepository: CompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<List<Playable>> = withContext(coroutineDispatcher) {
        compilationRepository.observeCompilations()
    }
}
