package me.samuki.domain.filters.activeFilters

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.filters.ObserveAllFilters
import me.samuki.model.filters.Filter
import me.samuki.model.filters.ListType
import me.samuki.model.filters.ListTypeFilter
import javax.inject.Inject

public class ObserveActiveListType @Inject constructor(
    private val observeAllFilters: ObserveAllFilters,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<ListType> = withContext(coroutineDispatcher) {
        observeAllFilters().map {
            it.findListType()
        }
    }.distinctUntilChanged()

    private fun List<Filter>.findListType(): ListType =
        filterIsInstance<ListTypeFilter>().firstOrNull()?.listType ?: ListType.All
}
