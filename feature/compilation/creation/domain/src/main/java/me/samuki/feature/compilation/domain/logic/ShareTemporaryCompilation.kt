package me.samuki.feature.compilation.domain.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.playable.SharePlayable
import me.samuki.domain.rail.andThen
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class ShareTemporaryCompilation @Inject constructor(
    private val prepareTemporaryCompilation: PrepareTemporaryCompilation,
    private val sharePlayable: SharePlayable,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) {

    public suspend operator fun invoke(): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            prepareTemporaryCompilation().andThen { temporaryCompilation ->
                sharePlayable(temporaryCompilation)
            }
        }
}
