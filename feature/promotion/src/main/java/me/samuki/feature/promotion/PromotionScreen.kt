package me.samuki.feature.promotion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.core.ui.composables.repeatable.SimpleToolbar
import me.samuki.core.ui.value.Label
import me.samuki.feature.promotion.list.PromotionList
import me.samuki.feature.promotion.list.PromotionListState
import me.samuki.feature.promotion.preview.PreviewPromotionListStateProvider

@Composable
public fun PromotionScreen(
    navigation: PromotionNavigation
) {
    val viewModel: PromotionViewModel = hiltViewModel()

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
            title = Label(stringResource(R.string.promotion_toolbar_name)),
            goBack = { onEvent(PromotionContract.Event.GoBack) }
        )
        PromotionList(
            state.listState,
            onEvent,
        )
    }
}

@Preview
@Composable
private fun PromotionScreenPreview(
    @PreviewParameter(PreviewPromotionListStateProvider::class) state: PromotionListState,
) = Surface {
    PromotionContent(
        state = PromotionContract.State(
            listState = state
        ),
    ) { }
}
