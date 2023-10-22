package me.samuki.resource.set.permissions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import me.samuki.model.NoAnswer
import javax.inject.Inject


public class SettingsOpeningHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    public fun openSettings(): NoAnswer {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.setData(uri)
        Log.d("OPEN_SETTINGS", "Start activity open settings intent")
        context.startActivity(intent)
        return NoAnswer
    }

}