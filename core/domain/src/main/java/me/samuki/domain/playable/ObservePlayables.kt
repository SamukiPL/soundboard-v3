package me.samuki.domain.playable

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.domain.compilation.observe.ObserveAllCompilations
import me.samuki.domain.params.ListType
import me.samuki.domain.search.GetSearchQuery
import me.samuki.domain.sound.observe.ObserveAllSounds
import me.samuki.domain.sound.observe.ObserveFavourites
import me.samuki.model.Playable
import me.samuki.model.values.Query
import javax.inject.Inject

public class ObservePlayables @Inject constructor(
    private val observeAllSounds: ObserveAllSounds,
    private val observeFavourites: ObserveFavourites,
    private val observeCompilations: ObserveAllCompilations,
    private val getSearchQuery: GetSearchQuery,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(listType: ListType): Flow<List<Playable>> =
        withContext(coroutineDispatcher) {
            when (listType) {
                ListType.All -> observeAllSounds()
                ListType.Favourites -> observeFavourites()
                ListType.Compilations -> observeCompilations()
            }
                .combine(getSearchQuery()) { playables, query ->
                    when (query) {
                        Query.Empty -> playables
                        is Query.Text -> filterByText(playables, query.value)
                    }
                }
        }

    private fun filterByText(playables: List<Playable>, query: String) = playables.filter {
        it.name.value.startsWith(query)
    } + playables.filter {
        it.name.value.contains(query)
    }.distinct()
}
