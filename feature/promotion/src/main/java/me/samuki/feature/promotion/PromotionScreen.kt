package me.samuki.feature.promotion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.core.ui.composables.repeatable.SimpleToolbar
import me.samuki.core.ui.value.Label
import me.samuki.feature.promotion.list.PromotionView

@Composable
public fun PromotionScreen(
    navigation: PromotionNavigation
) {
    val viewModel: PromotionViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(PromotionContract.Event.Init)
    }

    ObserveAsEvents(viewModel.eventsFlow) {
        when (it) {
            PromotionContract.Effect.GoBackToList -> navigation.goBackToList()
            is PromotionContract.Effect.GoToPromotion -> navigation.goToSoundboard(it.url)
        }
    }

    val state = remember { viewModel.state }
    val onEvent by remember { mutableStateOf(viewModel::onEvent) }

    PromotionContent(state, onEvent)
}

@Composable
private fun PromotionContent(
    state: PromotionContract.State,
    onEvent: (PromotionContract.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        SimpleToolbar(
            title = Label("TODO"),
            goBack = { onEvent(PromotionContract.Event.GoBack) }
        )
        LazyColumn {
            items(
                items = state.promotions,
            ) { item ->
                PromotionView(
                    item = item,
                    onEvent = onEvent
                )
            }
        }
    }

}
