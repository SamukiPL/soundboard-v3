package me.samuki.domain.sound.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.common.ext.filterList
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.values.LikeState
import javax.inject.Inject

class ObserveFavourites @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = withContext(coroutineDispatcher) {
        soundRepository.observeSounds()
            .filterList { it.likeState == LikeState.Favourite }
    }
}
