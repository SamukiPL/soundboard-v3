package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Playable
import me.samuki.model.values.LikeState
import javax.inject.Inject

class ChangeFavouriteState @Inject constructor(
    private val makeSoundFavourite: MakeSoundFavourite,
    private val makeSoundNormal: MakeSoundNormal,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(sound: Playable.Sound) = withContext(coroutineDispatcher) {
        when (sound.likeState) {
            LikeState.Favourite -> makeSoundNormal(sound)
            LikeState.Normal -> makeSoundFavourite(sound)
        }
    }
}
