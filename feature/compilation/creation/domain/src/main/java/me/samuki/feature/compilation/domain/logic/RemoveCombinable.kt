package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class RemoveCombinable @Inject constructor(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(item: CombinedCombinable): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            repository.removeCombinable(item)
        }
}
