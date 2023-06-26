package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.search.SearchRepository
import me.samuki.model.values.Query
import javax.inject.Inject

class SearchByQuery @Inject constructor(
    private val searchRepository: SearchRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(query: Query) = withContext(coroutineDispatcher) {
        when (query) {
            Query.Empty -> searchRepository.clearQuery()
            is Query.Text -> searchRepository.setSearchQuery(query)
        }
    }
}
