package me.samuki.model.exceptions

public class WriteSettingsPermissionNotGrantedException :
    Throwable(MESSAGE, cause = IllegalStateException()) {

    private companion object {
        const val MESSAGE = "Write Settings Permission was not granted by user"
    }
}
