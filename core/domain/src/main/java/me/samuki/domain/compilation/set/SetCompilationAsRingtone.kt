package me.samuki.domain.compilation.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class SetCompilationAsRingtone @Inject constructor(
    private val setCompilationRepository: SetCompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(compilation: Compilation): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            setCompilationRepository.setAsRingtone(compilation)
        }
}
