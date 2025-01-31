package me.samuki.feature.list.items.playable

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.items.PlayableItem
import me.samuki.feature.list.items.options.OptionsChooser
import me.samuki.feature.list.items.options.OptionsState
import me.samuki.model.Key
import me.samuki.model.Sound
import me.samuki.model.util.EMPTY_STRING
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path
import me.samuki.model.values.Supplement

@Composable
internal fun PlayableView(
    playableItem: PlayableItem,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var optionsState by remember { mutableStateOf(OptionsState.Closed) }
    AnimatedContent(
        targetState = optionsState,
        modifier = modifier,
        transitionSpec = {
            slideInHorizontally {
                if (optionsState.isOpen()) it else -it
            }.togetherWith(slideOutHorizontally {
                if (optionsState.isClosed()) it else -it
            })
        },
        label = EMPTY_STRING
    ) {
        when (it) {
            OptionsState.Closed -> DefaultView(
                playableItem,
                onEvent = onEvent,
                openOptions = { optionsState = OptionsState.Open }
            )
            OptionsState.Open -> OptionsChooser(
                playable = playableItem.playable,
                onEvent = onEvent,
                closeOptions = { optionsState = OptionsState.Closed }
            )
        }
    }
}

private fun OptionsState.isOpen(): Boolean = when (this) {
    OptionsState.Open -> true
    OptionsState.Closed -> false
}

private fun OptionsState.isClosed(): Boolean = when (this) {
    OptionsState.Open -> false
    OptionsState.Closed -> true
}


@Preview
@Composable
private fun PlayableViewPreview() {
    val playable = Sound(
        id = Id(1),
        supplement = Supplement(EMPTY_STRING),
        name = Name(EMPTY_STRING),
        likeState = LikeState.Normal,
        path = Path(Uri.EMPTY),
    )
    PlayableView(
        playableItem = PlayableItem(
            key = Key(Id(1), Supplement(EMPTY_STRING)),
            name = Name("Test Name For Test"),
            playable = playable,
            likeable = playable,
        ),
        onEvent = {},
        modifier = Modifier.height(64.dp)
    )
}
