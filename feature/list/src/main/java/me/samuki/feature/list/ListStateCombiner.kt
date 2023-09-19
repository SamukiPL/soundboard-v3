package me.samuki.feature.list

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.samuki.feature.list.items.toListItem
import me.samuki.feature.list.toolbar.QueryButtonVisible
import me.samuki.feature.list.toolbar.ToolbarType
import me.samuki.feature.list.toolbar.filters.toFilterItem
import me.samuki.model.Playable
import me.samuki.model.filters.Filter
import me.samuki.model.values.Query
import me.samuki.model.values.getQueryValue

internal class ListStateCombiner(
    private val update: ((ListContract.State) -> ListContract.State) -> Unit
) {

    suspend fun manageStateForPlayablesAndFilters(
        playablesFlow: Flow<List<Playable>>,
        filtersFlow: Flow<List<Filter>>,
    ) {
        coroutineScope {
            launch {
                playablesFlow.collect { playables ->
                    update { state ->
                        state.copy(
                            items = playables.map { it.toListItem() },
                        )
                    }
                }
            }
            launch {
                filtersFlow.collect { filters ->
                    update { state ->
                        state.copy(
                            toolbarState = state.toolbarState.copy(
                                filters = filters.map { it.toFilterItem() }
                            )
                        )
                    }
                }
            }
        }
    }

    fun openQueryToolbar(query: Query) {
        update { state ->
            state.copy(
                toolbarState = state.toolbarState.copy(
                    type = ToolbarType.QueryInput,
                    query = query,
                    showQueryPlaceholder = query.getQueryValue().isEmpty()
                )
            )
        }
    }

    fun updateQuery(query: Query) {
        update { state ->
            state.copy(
                toolbarState = state.toolbarState.copy(
                    query = query,
                    acceptQueryEnable = query.getQueryValue().isNotEmpty(),
                    showQueryPlaceholder = query.getQueryValue().isEmpty()
                )
            )
        }
    }

    fun closeQueryToolbar(queryButtonVisible: QueryButtonVisible? = null) {
        update { state ->
            state.copy(
                toolbarState = state.toolbarState.copy(
                    type = ToolbarType.Filters,
                    showQueryButton = queryButtonVisible
                        ?: (state.toolbarState.query == Query.Empty)
                )
            )
        }
    }
}
