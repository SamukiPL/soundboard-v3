package me.samuki.domain.permissions

import me.samuki.model.NoAnswer
import me.samuki.model.PermissionState

public interface PermissionsRepository {

    public fun writeSettingsPermissionState(): Result<PermissionState>

    public fun openSettings(): Result<NoAnswer>
}
