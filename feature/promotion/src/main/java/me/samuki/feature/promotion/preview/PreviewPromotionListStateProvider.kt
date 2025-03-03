package me.samuki.feature.promotion.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.feature.promotion.list.PromotionListState

internal class PreviewPromotionListStateProvider(
    private val previewPromotionProvider: PreviewPromotionProvider = PreviewPromotionProvider()
) : PreviewParameterProvider<PromotionListState> {

    override val values: Sequence<PromotionListState>
        get() = sequenceOf(
            PromotionListState.AdsAcceptation,
            PromotionListState.Loading,
            PromotionListState.Online(
                List(10) { previewPromotionProvider.values.first() }
            ),
            PromotionListState.Offline(
                List(10) { previewPromotionProvider.values.first() }
            ),
            PromotionListState.Error
        )
}
