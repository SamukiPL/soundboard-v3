package me.samuki.feature.list.items.options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.RingVolume
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

internal enum class Options(
    val icon: ImageVector,
    val contentDescription: String,
    val weight: Float,
) {
    SetAsNotification(
        icon = Icons.Filled.NotificationsActive,
        contentDescription = "Notifications",
        weight = 1f
    ),
    SetAsRingtone(
        icon = Icons.Filled.RingVolume,
        contentDescription = "Ringtone",
        weight = 1f
    ),
    Share(
        icon = Icons.Filled.Share,
        contentDescription = "Share",
        weight = 1f
    ),
    Close(
        icon = Icons.Filled.Close,
        contentDescription = "Close",
        weight = 0.5f
    )
}
