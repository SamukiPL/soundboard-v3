package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.list.ListScreen
import me.samuki.navigation.provided.ProvidedListNavigation

@Composable
@Destination
@RootNavGraph(
    start = true
)
internal fun List(
    navigator: DestinationsNavigator
) {
    ListScreen(
        navigation = ProvidedListNavigation(navigator)
    )
}
