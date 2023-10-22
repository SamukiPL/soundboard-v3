package me.samuki.resource.set.permissions

import me.samuki.model.PermissionState

public interface WriteSettingsPermissionChecker {

    public operator fun invoke(): PermissionState
}
