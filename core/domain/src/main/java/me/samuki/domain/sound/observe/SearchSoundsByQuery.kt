package me.samuki.domain.sound.observe

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.values.Query
import me.samuki.domain.sound.SoundRepository
import javax.inject.Inject

class SearchSoundsByQuery @Inject constructor(
    private val soundRepository: SoundRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(query: Query) = withContext(coroutineDispatcher) {
        soundRepository.searchByQuery(query)
    }
}
