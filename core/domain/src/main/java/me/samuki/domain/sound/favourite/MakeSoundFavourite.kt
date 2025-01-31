package me.samuki.domain.sound.favourite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Likeable
import me.samuki.model.NoAnswer
import javax.inject.Inject

public class MakeSoundFavourite @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(likeable: Likeable): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            soundRepository.makeFavourite(likeable)
        }
}
