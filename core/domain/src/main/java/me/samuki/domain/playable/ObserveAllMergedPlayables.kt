package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.observe.ObserveAllCompilations
import me.samuki.domain.sound.observe.ObserveAllSounds
import me.samuki.model.Playable
import javax.inject.Inject

public class ObserveAllMergedPlayables @Inject constructor(
    private val observeAllSounds: ObserveAllSounds,
    private val observeAllCompilations: ObserveAllCompilations,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<List<Playable>> = withContext(coroutineDispatcher) {
        observeAllSounds().combine(observeAllCompilations()) { sounds, compilations ->
            sounds + compilations
        }
    }
}
