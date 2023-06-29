package me.samuki.domain.compilation.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Compilation
import javax.inject.Inject

class SetCompilationAsRingtone @Inject constructor(
    private val setCompilationRepository: SetCompilationRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(compilation: Compilation) =
        withContext(coroutineDispatcher) {
            setCompilationRepository.setAsRingtone(compilation)
        }
}
