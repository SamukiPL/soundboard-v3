package me.samuki.feature.promotion

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.feature.promotion.list.PromotionListState
import me.samuki.model.values.StoreUrl

internal interface PromotionContract {

    @Stable
    class State(
        listState: PromotionListState = PromotionListState.AdsAcceptation,
    ) {
        var listState by mutableStateOf(listState)
    }

    sealed interface Event {
        data object AcceptAds : Event

        data object GoBack : Event

        data class ShowPromotedSoundboard(val url: StoreUrl) : Event

        data object ShowStoreProfile : Event
    }

    sealed interface Effect {
        data object GoBackToList : Effect

        data class GoToPromotion(val url: StoreUrl) : Effect
    }
}
