package me.samuki.feature.promotion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.samuki.domain.promotion.GetPromotions
import me.samuki.feature.promotion.list.toItem
import javax.inject.Inject

@HiltViewModel
internal class PromotionViewModel @Inject constructor(
    private val getPromotions: GetPromotions,
) : ViewModel() {

    internal var state: PromotionContract.State by mutableStateOf(PromotionContract.State())
        private set

    private val eventChannel = Channel<PromotionContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onEvent(event: PromotionContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: PromotionContract.Event): Any = when (event) {
        PromotionContract.Event.Init -> getPromotions()
            .onSuccess { promotions ->
                state.promotions = promotions.map { it.toItem() }
            }
        PromotionContract.Event.GoBack -> eventChannel.send(PromotionContract.Effect.GoBackToList)
        is PromotionContract.Event.ShowPromotedSoundboard -> eventChannel.send(
            PromotionContract.Effect.GoToPromotion(
                event.url
            )
        )
    }
}
