package me.samuki.feature.list.items.options

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.model.Playable

@Composable
internal fun OptionsChooser(
    playable: Playable,
    onEvent: (ListContract.Event) -> Unit,
    closeOptions: () -> Unit,
    modifier: Modifier = Modifier
) {
    val optionsAction: (Options) -> Unit = {
        when (it) {
            Options.SetAsNotification -> onEvent(ListContract.Event.SetAs.notification(playable))
            Options.SetAsRingtone -> onEvent(ListContract.Event.SetAs.ringtone(playable))
            Options.Share -> onEvent(ListContract.Event.Share(playable))
            Options.Close -> closeOptions()
        }
    }

    Row(
        modifier = modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Options.entries.forEach { option ->
            IconButton(
                onClick = { optionsAction(option) },
                modifier = Modifier
                    .weight(option.weight)
                    .background(option.getBackground())
            ) {
                Icon(
                    imageVector = option.icon,
                    contentDescription = option.contentDescription,
                    tint = option.getTint(),
                    modifier = Modifier
                        .size(28.dp)
                )
            }
        }
    }
}

@Composable
private fun Options.getBackground(): Color = when (this) {
    Options.SetAsNotification -> MaterialTheme.colorScheme.primaryContainer
    Options.SetAsRingtone -> MaterialTheme.colorScheme.inversePrimary
    Options.Share -> MaterialTheme.colorScheme.primaryContainer
    Options.Close -> MaterialTheme.colorScheme.error
}

@Composable
private fun Options.getTint(): Color = when (this) {
    Options.Close -> MaterialTheme.colorScheme.onError
    else -> MaterialTheme.colorScheme.onPrimaryContainer
}
