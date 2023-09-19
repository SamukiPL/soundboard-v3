package me.samuki.domain.search

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query
import me.samuki.model.values.getQueryValue
import javax.inject.Inject

public class SearchByQuery @Inject constructor(
    private val searchRepository: SearchRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(query: Query): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (val ensuredQuery = query.ensureEmptiness()) {
                Query.Empty -> searchRepository.clearQuery()
                is Query.Text -> searchRepository.setSearchQuery(ensuredQuery)
            }
        }

    private fun Query.ensureEmptiness(): Query = if (getQueryValue().isNotEmpty())
        this
    else
        Query.Empty
}
