package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.promotion.PromotionScreen
import me.samuki.navigation.provided.ProvidedPromotionNavigation

@Composable
@Destination
@RootNavGraph
internal fun Promotion(
    navigator: DestinationsNavigator
) {
    PromotionScreen(
        navigation = ProvidedPromotionNavigation(
            context = LocalContext.current,
            navigator = navigator,
        )
    )
}
