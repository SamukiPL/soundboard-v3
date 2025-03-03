package me.samuki.feature.promotion

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.feature.promotion.list.PromotionItem
import me.samuki.model.values.SoundboardUrl

internal interface PromotionContract {

    @Stable
    class State(
        promotions: List<PromotionItem> = mutableStateListOf()
    ) {
        var promotions: List<PromotionItem> by mutableStateOf(promotions)
    }

    sealed interface Event {
        data object Init : Event

        data object GoBack : Event

        data class ShowPromotedSoundboard(val url: SoundboardUrl) : Event
    }

    sealed interface Effect {
        data object GoBackToList : Effect

        data class GoToPromotion(val url: SoundboardUrl) : Effect
    }
}
