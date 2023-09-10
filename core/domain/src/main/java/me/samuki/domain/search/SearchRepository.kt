package me.samuki.domain.search

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query

public interface SearchRepository {

    public val queryFlow: Flow<Query>

    public suspend fun setSearchQuery(query: Query.Text): Result<NoAnswer>

    public suspend fun clearQuery(): Result<NoAnswer>
}
