package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.model.NoAnswer
import me.samuki.model.values.Name
import javax.inject.Inject

public class EndCreation @Inject constructor(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(name: Name): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            repository.endCreation(name)
        }
}
