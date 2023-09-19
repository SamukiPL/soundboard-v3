package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.observe.ObserveAllCompilations
import me.samuki.domain.filters.activeFilters.ObserveActiveListType
import me.samuki.domain.search.ObserveSearchQuery
import me.samuki.domain.sound.observe.ObserveAllSounds
import me.samuki.domain.sound.observe.ObserveFavourites
import me.samuki.model.Playable
import me.samuki.model.filters.ListType
import me.samuki.model.values.Query
import javax.inject.Inject

public class ObservePlayables @Inject constructor(
    private val observeActiveListType: ObserveActiveListType,
    private val observeSearchQuery: ObserveSearchQuery,
    private val observeAllSounds: ObserveAllSounds,
    private val observeFavourites: ObserveFavourites,
    private val observeCompilations: ObserveAllCompilations,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(): Flow<List<Playable>> =
        withContext(coroutineDispatcher) {
            observeActiveListType().flatMapLatest { listType ->
                when (listType) {
                    ListType.All -> observeAllSounds()
                    ListType.Favourites -> observeFavourites()
                    ListType.Compilations -> observeCompilations()
                }.flatMapLatest { playables ->
                    observeSearchQuery().map { query ->
                        when (query) {
                            Query.Empty -> playables
                            is Query.Text -> filterByText(playables, query.value)
                        }
                    }
                }
            }.distinctUntilChanged()
        }

    private fun filterByText(playables: List<Playable>, query: String): List<Playable> {
        val filtered = playables.filter {
            it.name.value.startsWith(query)
        } + playables.filter {
            it.name.value.contains(query)
        }
        return filtered.distinctBy { it.id.value }
    }
}
