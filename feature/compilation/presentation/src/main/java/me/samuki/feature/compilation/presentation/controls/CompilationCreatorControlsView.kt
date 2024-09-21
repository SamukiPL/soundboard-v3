@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.controls

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.previews.PreviewBooleanProvider
import me.samuki.feature.compilation.presentation.CreateButtonVisible
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.VolumeEnabled

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorControlsView(
    showCreateButton: CreateButtonVisible,
    volumeEnabled: VolumeEnabled,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = { onEvent(CreatorContract.Event.GoBack) },
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(1f)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Close",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        IconButton(
            onClick = { onEvent(CreatorContract.Event.ChangeVolume) },
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(1f),
        ) {
            Icon(
                imageVector = if (volumeEnabled)
                    Icons.AutoMirrored.Filled.VolumeUp
                else
                    Icons.AutoMirrored.Filled.VolumeOff,
                contentDescription = "Volume",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        IconButton(
            onClick = { onEvent(CreatorContract.Event.PlayCompilation) },
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        IconButton(
            onClick = { onEvent(CreatorContract.Event.ShareCompilation) },
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(1f),
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Share",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        AnimatedContent(
            targetState = showCreateButton,
            label = "Create Button",
            modifier = Modifier
                .clip(CircleShape)
                .aspectRatio(1f)
        ) {
            if (it) {
                IconButton(
                    onClick = { onEvent(CreatorContract.Event.StartSettingName) },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "Dialog Bounds"),
                            animatedVisibilityScope = this@AnimatedVisibilityScope,
                        )
                        .animateEnterExit()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Create",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            } else {
                Box {}
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCompilationCreatorControlsView(
    @PreviewParameter(PreviewBooleanProvider::class) flag: Boolean,
) {
    SharedTransitionLayout {
        AnimatedVisibility(true) {
            CompilationCreatorControlsView(
                flag,
                flag,
                {},
            )
        }
    }
}
