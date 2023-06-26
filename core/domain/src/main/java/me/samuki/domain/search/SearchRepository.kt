package me.samuki.domain.search

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query

interface SearchRepository {

    val queryFlow: Flow<Query>

    suspend fun setSearchQuery(query: Query.Text): Result<NoAnswer>

    suspend fun clearQuery(): Result<NoAnswer>
}
