package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Likeable
import me.samuki.model.NoAnswer
import me.samuki.model.values.LikeState
import javax.inject.Inject

public class ChangeFavouriteState @Inject constructor(
    private val makeSoundFavourite: MakeSoundFavourite,
    private val makeSoundNormal: MakeSoundNormal,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(likeable: Likeable): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (likeable.likeState) {
                LikeState.Favourite -> makeSoundNormal(likeable)
                LikeState.Normal -> makeSoundFavourite(likeable)
            }
        }
}
