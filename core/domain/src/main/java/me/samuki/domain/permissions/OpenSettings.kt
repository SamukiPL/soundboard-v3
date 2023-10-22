package me.samuki.domain.permissions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.NoAnswer
import me.samuki.model.PermissionState
import me.samuki.model.exceptions.WriteSettingsPermissionNotGrantedException
import javax.inject.Inject

public class OpenSettings @Inject constructor(
    private val permissionsRepository: PermissionsRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke() : Result<NoAnswer> =
        withContext(coroutineDispatcher) {
            permissionsRepository.openSettings()
        }
}