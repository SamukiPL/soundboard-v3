package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.observe.ObserveAllCompilations
import me.samuki.domain.params.ListType
import me.samuki.domain.sound.observe.ObserveAllSounds
import me.samuki.domain.sound.observe.ObserveFavourites
import me.samuki.model.Playable
import javax.inject.Inject

class ObservePlayables @Inject constructor(
    private val observeAllSounds: ObserveAllSounds,
    private val observeFavourites: ObserveFavourites,
    private val observeCompilations: ObserveAllCompilations,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(listType: ListType): Flow<List<Playable>> =
        withContext(coroutineDispatcher) {
            when (listType) {
                ListType.All -> observeAllSounds()
                ListType.Favourites -> observeFavourites()
                ListType.Compilations -> observeCompilations()
            }
        }
}
