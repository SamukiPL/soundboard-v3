package me.samuki.domain.filters

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.filters.Filter
import javax.inject.Inject

public class ObserveAllFilters @Inject constructor(
    private val filtersRepository: FiltersRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Flow<List<Filter>> =
        withContext(coroutineDispatcher) {
            filtersRepository.allFilters
        }
}
