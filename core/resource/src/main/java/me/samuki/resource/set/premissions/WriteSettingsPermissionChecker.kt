package me.samuki.resource.set.premissions

import me.samuki.model.PermissionState

public interface WriteSettingsPermissionChecker {

    public operator fun invoke(): PermissionState
}
