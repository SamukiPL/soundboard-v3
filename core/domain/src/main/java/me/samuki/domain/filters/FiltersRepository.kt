package me.samuki.domain.filters

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.filters.Filter

public interface FiltersRepository {

    public val allFilters: Flow<List<Filter>>

    public suspend fun selectFilter(filter: Filter): Result<NoAnswer>

    public suspend fun removeFilter(filter: Filter): Result<NoAnswer>
}
