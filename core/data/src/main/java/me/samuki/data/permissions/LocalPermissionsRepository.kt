package me.samuki.data.permissions

import me.samuki.domain.permissions.PermissionsRepository
import me.samuki.model.NoAnswer
import me.samuki.model.PermissionState
import me.samuki.resource.set.permissions.SettingsOpeningHandler
import me.samuki.resource.set.permissions.WriteSettingsPermissionChecker
import javax.inject.Inject

internal class LocalPermissionsRepository @Inject constructor(
    private val writeSettingsPermissionChecker: WriteSettingsPermissionChecker,
    private val settingsOpeningHandler: SettingsOpeningHandler
) : PermissionsRepository {
    override fun writeSettingsPermissionState(): Result<PermissionState> = runCatching {
        writeSettingsPermissionChecker()
    }

    override fun openSettings(): Result<NoAnswer> = runCatching { settingsOpeningHandler.openSettings() }
}
