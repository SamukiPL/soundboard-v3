package me.samuki.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
public fun ListScreen(
    @Suppress("UNUSED_PARAMETER") navigation: ListNavigation
) {
    val viewModel: ListViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ListContract.Event.Init)
    }

    val playables = viewModel.state.collectAsState().value.items

    LazyColumn {
        items(playables) {
            Text(it.name.value, modifier = Modifier.clickable {
                viewModel.onEvent(ListContract.Event.Play(it))
            })
        }
    }
}
