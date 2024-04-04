package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import javax.inject.Inject

public class AddSound @Inject constructor(
    private val repository: CompilationCreationFeatureRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(sound: Sound): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            repository.addSound(sound)
        }
}
