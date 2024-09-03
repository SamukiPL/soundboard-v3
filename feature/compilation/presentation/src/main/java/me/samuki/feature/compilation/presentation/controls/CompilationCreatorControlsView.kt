package me.samuki.feature.compilation.presentation.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract

@Composable
internal fun CompilationCreatorControlsView(
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().height(48.dp).padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = { onEvent(CreatorContract.Event.GoBack) },
            modifier =
                Modifier.clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Close",
            )
        }
        Box(modifier = Modifier.aspectRatio(1f))
        IconButton(
            onClick = { onEvent(CreatorContract.Event.PlayCompilation) },
            modifier =
                Modifier.clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
            )
        }
        IconButton(
            onClick = { onEvent(CreatorContract.Event.ShareCompilation) },
            modifier =
                Modifier.padding(4.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Close",
                modifier = Modifier.size(16.dp),
            )
        }
        IconButton(
            onClick = { onEvent(CreatorContract.Event.EndCreation) },
            modifier =
                Modifier.clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCompilationCreatorControlsView() {
    CompilationCreatorControlsView({})
}
