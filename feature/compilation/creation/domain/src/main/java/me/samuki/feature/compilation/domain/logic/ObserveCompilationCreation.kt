package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import javax.inject.Inject

public class ObserveCompilationCreation @Inject constructor(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<List<CombinedCombinable>> = withContext(coroutineDispatcher) {
        repository.items
    }
}
