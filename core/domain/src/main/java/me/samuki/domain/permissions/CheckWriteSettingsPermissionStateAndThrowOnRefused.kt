package me.samuki.domain.permissions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import me.samuki.model.PermissionState
import me.samuki.model.exceptions.WriteSettingsPermissionNotGrantedException
import javax.inject.Inject

public class CheckWriteSettingsPermissionStateAndThrowOnRefused @Inject constructor(
    private val permissionsRepository: PermissionsRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    public suspend operator fun invoke(): Result<PermissionState> =
        withContext(coroutineDispatcher) {
            permissionsRepository.writeSettingsPermissionState().mapCatching {
                when (it) {
                    PermissionState.Granted -> it
                    PermissionState.Refused -> throw WriteSettingsPermissionNotGrantedException()
                }
            }
        }
}
