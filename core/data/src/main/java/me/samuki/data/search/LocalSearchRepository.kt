package me.samuki.data.search

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import me.samuki.domain.rail.runNoAnswer
import me.samuki.domain.search.SearchRepository
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
internal class LocalSearchRepository @Inject constructor() : SearchRepository {

    override val queryFlow = MutableStateFlow<Query>(Query.Empty)

    override suspend fun setSearchQuery(query: Query.Text): Result<NoAnswer> = runNoAnswer {
        queryFlow.emit(query)
    }

    override suspend fun clearQuery(): Result<NoAnswer> = runNoAnswer {
        queryFlow.emit(Query.Empty)
    }
}
