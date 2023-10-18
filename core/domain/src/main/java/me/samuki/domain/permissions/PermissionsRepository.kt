package me.samuki.domain.permissions

import me.samuki.model.PermissionState

public interface PermissionsRepository {

    public fun writeSettingsPermissionState(): Result<PermissionState>
}
