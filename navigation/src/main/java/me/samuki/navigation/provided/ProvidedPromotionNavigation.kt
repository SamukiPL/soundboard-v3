package me.samuki.navigation.provided

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.promotion.PromotionNavigation
import me.samuki.model.values.SoundboardUrl

internal class ProvidedPromotionNavigation(
    private val context: Context,
    private val navigator: DestinationsNavigator
) : PromotionNavigation {

    override fun goBackToList() {
        navigator.navigateUp()
    }

    override fun goToSoundboard(url: SoundboardUrl) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url.value)).run {
            context.startActivity(this)
        }
    }
}
