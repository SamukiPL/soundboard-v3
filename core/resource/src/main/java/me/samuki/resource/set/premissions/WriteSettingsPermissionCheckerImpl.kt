package me.samuki.resource.set.premissions

import android.content.Context
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import me.samuki.model.PermissionState
import javax.inject.Inject

internal class WriteSettingsPermissionCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WriteSettingsPermissionChecker {

    override operator fun invoke(): PermissionState = when (Settings.System.canWrite(context)) {
        true -> PermissionState.Granted
        false -> PermissionState.Refused
    }
}
