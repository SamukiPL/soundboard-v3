package me.samuki.domain.compilation.set

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.lint.InternalDomainApi
import me.samuki.domain.params.SetType
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import javax.inject.Inject
@InternalDomainApi
public class SetCompilation @Inject constructor(
    private val setCompilationAsNotification: SetCompilationAsNotification,
    private val setCompilationAsRingtone: SetCompilationAsRingtone,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(compilation: Compilation, setType: SetType): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (setType) {
                SetType.Notification -> setCompilationAsNotification(compilation)
                SetType.Ringtone -> setCompilationAsRingtone(compilation)
            }
        }
}
