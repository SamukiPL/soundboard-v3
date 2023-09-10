package me.samuki.domain.search

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.values.Query
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

public class GetSearchQuery @Inject constructor(
    private val searchRepository: SearchRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<Query> = withContext(coroutineDispatcher) {
        searchRepository.queryFlow
            .debounce {
                when (it) {
                    Query.Empty -> ZERO_DEBOUNCE.seconds
                    is Query.Text -> DEBOUNCE_SECONDS.seconds
                }
            }
    }

    private companion object {
        private const val ZERO_DEBOUNCE = 0
        private const val DEBOUNCE_SECONDS = 3
    }
}
