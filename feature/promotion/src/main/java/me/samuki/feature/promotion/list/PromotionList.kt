package me.samuki.feature.promotion.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import me.samuki.feature.promotion.PromotionContract
import me.samuki.feature.promotion.preview.PreviewPromotionListStateProvider
import me.samuki.feature.promotion.ui.OfflineLabel
import me.samuki.feature.promotion.ui.PromotionAdsAcceptationView
import me.samuki.feature.promotion.ui.PromotionErrorView
import me.samuki.feature.promotion.ui.PromotionLoadingView

@Composable
internal fun PromotionList(
    state: PromotionListState,
    onEvent: (PromotionContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) = Column {
    Crossfade(
        targetState = state,
        modifier = modifier
    ) {
        when (it) {
            PromotionListState.AdsAcceptation -> PromotionAdsAcceptationView(
                onEvent = onEvent
            )
            PromotionListState.Loading -> PromotionLoadingView()
            is PromotionListState.Offline -> Column {
                OfflineLabel()
                PromotionLazyList(
                    items = it.promotions,
                    onEvent = onEvent,
                )
            }
            is PromotionListState.Online -> PromotionLazyList(
                items = it.promotions,
                onEvent = onEvent,
            )
            PromotionListState.Error -> PromotionErrorView(
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun ColumnScope.PromotionLazyList(
    items: List<PromotionItem>,
    onEvent: (PromotionContract.Event) -> Unit,
    modifier: Modifier = Modifier
) = LazyColumn(modifier = modifier.weight(1f)) {
    items(
        items = items,
    ) { item ->
        PromotionView(
            item = item,
            onEvent = onEvent
        )
    }
}

@Preview
@Composable
private fun PromotionListPreview(
    @PreviewParameter(PreviewPromotionListStateProvider::class) state: PromotionListState,
) {
    Surface {
        PromotionList(
            state = state,
            onEvent = {}
        )
    }
}
