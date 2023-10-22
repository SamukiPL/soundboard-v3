package me.samuki.rationale.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.domain.params.SetType
import me.samuki.feature.rationale.R
import me.samuki.model.Playable
import me.samuki.rationale.logic.RationaleContract
import me.samuki.rationale.logic.RationaleNavigation
import me.samuki.rationale.logic.RationaleViewModel

@Composable
public fun RationaleScreen(
    playable: Playable, setType: SetType, rationaleNavigation: RationaleNavigation
) {
    val viewModel: RationaleViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RationaleContract.Event.SetupScreen(playable, setType))

        viewModel.eventsFlow.collect {
            when (it) {
                RationaleContract.Effect.GoBackToList -> rationaleNavigation.backToList()
            }
        }
    }

    val state = viewModel.state.collectAsState().value

    RationaleContent(
        state,
    ) { viewModel.onEvent(it) }
}