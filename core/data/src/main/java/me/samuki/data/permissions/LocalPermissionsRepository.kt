package me.samuki.data.permissions

import me.samuki.domain.permissions.PermissionsRepository
import me.samuki.model.PermissionState
import me.samuki.resource.set.premissions.WriteSettingsPermissionChecker
import javax.inject.Inject

internal class LocalPermissionsRepository @Inject constructor(
    private val writeSettingsPermissionChecker: WriteSettingsPermissionChecker
) : PermissionsRepository {
    override fun writeSettingsPermissionState(): Result<PermissionState> = runCatching {
        writeSettingsPermissionChecker()
    }
}
