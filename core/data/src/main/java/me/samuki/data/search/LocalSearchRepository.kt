package me.samuki.data.search

import kotlinx.coroutines.flow.MutableStateFlow
import me.samuki.domain.search.SearchRepository
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query

internal class LocalSearchRepository : SearchRepository {

    override val queryFlow = MutableStateFlow<Query>(Query.Empty)

    override suspend fun setSearchQuery(query: Query.Text): Result<NoAnswer> {
        queryFlow.emit(query)
        return Result.success(NoAnswer)
    }

    override suspend fun clearQuery(): Result<NoAnswer> {
        queryFlow.emit(Query.Empty)
        return Result.success(NoAnswer)
    }
}
