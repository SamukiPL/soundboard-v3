package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.model.values.LikeState
import javax.inject.Inject

public class ChangeFavouriteState @Inject constructor(
    private val makeSoundFavourite: MakeSoundFavourite,
    private val makeSoundNormal: MakeSoundNormal,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(sound: Sound): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (sound.likeState) {
                LikeState.Favourite -> makeSoundNormal(sound)
                LikeState.Normal -> makeSoundFavourite(sound)
            }
        }
}
