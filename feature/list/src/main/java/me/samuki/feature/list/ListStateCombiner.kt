package me.samuki.feature.list

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
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
    private val state: ListContract.State
) {

    suspend fun manageStateForPlayablesAndFilters(
        playablesFlow: Flow<List<Playable>>,
        filtersFlow: Flow<List<Filter>>,
    ) = coroutineScope {
        launch {
            playablesFlow.combine(filtersFlow) { playables, filters ->
                state.items = playables.map { it.toListItem() }
                state.toolbarState.filters = filters.map { it.toFilterItem() }
            }.collect()
        }
    }

    fun openQueryToolbar(query: Query) {
        state.toolbarState.type = ToolbarType.QueryInput
        state.toolbarState.queryViewState.query = query
        state.toolbarState.queryViewState.showQueryPlaceholder = query.getQueryValue().isEmpty()
    }

    fun updateQuery(query: Query) {
        state.toolbarState.queryViewState.query = query
        state.toolbarState.queryViewState.acceptQueryEnable = query.getQueryValue().isNotEmpty()
        state.toolbarState.queryViewState.showQueryPlaceholder = query.getQueryValue().isEmpty()
    }

    fun closeQueryToolbar(queryButtonVisible: QueryButtonVisible? = null) {
        state.toolbarState.type = ToolbarType.QueryInput
        state.toolbarState.queryViewState.showQueryPlaceholder = (queryButtonVisible
            ?: (state.toolbarState.queryViewState.query == Query.Empty))
    }
}
