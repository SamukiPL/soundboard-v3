package me.samuki.feature.promotion.list

import androidx.compose.runtime.mutableStateListOf

internal sealed interface PromotionListState {

    data object AdsAcceptation : PromotionListState

    data object Loading : PromotionListState

    data class Online(
        val promotions: List<PromotionItem> = mutableStateListOf()
    ) : PromotionListState

    data class Offline(
        val promotions: List<PromotionItem> = mutableStateListOf()
    ) : PromotionListState

    data object Error : PromotionListState
}
