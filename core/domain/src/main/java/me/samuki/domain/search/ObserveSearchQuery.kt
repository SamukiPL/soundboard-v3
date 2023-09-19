package me.samuki.domain.search

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import me.samuki.model.values.Query
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

public class ObserveSearchQuery @Inject constructor(
    private val searchRepository: SearchRepository,
) {

    public operator fun invoke(): Flow<Query> = searchRepository.queryFlow
        .debounce {
            when (it) {
                Query.Empty -> ZERO_DEBOUNCE.seconds
                is Query.Text -> DEBOUNCE_MILLISECONDS.milliseconds
            }
        }.distinctUntilChanged()

    private companion object {
        private const val ZERO_DEBOUNCE = 0
        private const val DEBOUNCE_MILLISECONDS = 10
    }
}
