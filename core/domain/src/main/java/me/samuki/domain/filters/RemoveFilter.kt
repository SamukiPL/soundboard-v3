package me.samuki.domain.filters

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.NoAnswer
import me.samuki.model.filters.Filter
import javax.inject.Inject

public class RemoveFilter @Inject constructor(
    private val filtersRepository: FiltersRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) {

    public suspend operator fun invoke(filter: Filter): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            filtersRepository.removeFilter(filter)
        }
}
