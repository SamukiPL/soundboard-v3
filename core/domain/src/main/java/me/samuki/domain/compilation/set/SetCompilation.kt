package me.samuki.domain.compilation.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.params.SetType
import me.samuki.model.Compilation
import javax.inject.Inject

class SetCompilation @Inject constructor(
    private val setCompilationAsNotification: SetCompilationAsNotification,
    private val setCompilationAsRingtone: SetCompilationAsRingtone,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(compilation: Compilation, setType: SetType) =
        withContext(coroutineDispatcher) {
            when (setType) {
                SetType.Notification -> setCompilationAsNotification(compilation)
                SetType.Ringtone -> setCompilationAsRingtone(compilation)
            }
        }
}
