package me.samuki.domain.filters

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.NoAnswer
import me.samuki.model.filters.Filter
import javax.inject.Inject

public class ChangeFilterSelectionState @Inject constructor(
    private val selectFilter: SelectFilter,
    private val removeFilter: RemoveFilter,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(filter: Filter): Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            when (filter.selected.value) {
                true -> removeFilter(filter)
                false -> selectFilter(filter)
            }
        }
}
