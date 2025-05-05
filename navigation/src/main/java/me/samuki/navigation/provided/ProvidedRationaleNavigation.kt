package me.samuki.navigation.provided

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.rationale.RationaleNavigation

internal class ProvidedRationaleNavigation(
    private val context: Context,
    private val navigator: DestinationsNavigator
) : RationaleNavigation, DestinationsNavigator by navigator {
    override fun backToList() {
        navigateUp()
    }

    override fun navigateToSettings() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
            data = Uri.parse("package:${context.packageName}")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
