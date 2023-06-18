package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.observe.ClearCompilationSearch
import me.samuki.domain.compilation.observe.SearchCompilationsByQuery
import me.samuki.model.values.Query
import me.samuki.domain.sound.observe.ClearSoundsSearch
import me.samuki.domain.sound.observe.SearchSoundsByQuery
import javax.inject.Inject

class SearchByQuery @Inject constructor(
    private val searchSoundsByQuery: SearchSoundsByQuery,
    private val searchCompilationsByQuery: SearchCompilationsByQuery,
    private val clearSoundSearch: ClearSoundsSearch,
    private val clearCompilationSearch: ClearCompilationSearch,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(query: Query) = withContext(coroutineDispatcher) {
        if (query.isEmpty) {
            searchSoundsByQuery(query)
                .map {
                    searchCompilationsByQuery(query)
                }
        } else {
            clearSoundSearch()
                .map {
                    clearCompilationSearch()
                }
        }
    }
}
