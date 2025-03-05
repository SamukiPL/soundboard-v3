@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.controls

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.buttons.CircleIconButton
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
        CircleIconButton(
            onClick = { onEvent(CreatorContract.Event.GoBack) },
            contentDescription = "Close",
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            modifier = Modifier.fillMaxHeight()
        )
        CircleIconButton(
            onClick = { onEvent(CreatorContract.Event.ChangeVolume) },
            contentDescription = "Volume",
            imageVector = if (volumeEnabled)
                Icons.AutoMirrored.Filled.VolumeUp
            else
                Icons.AutoMirrored.Filled.VolumeOff,
            modifier = Modifier.fillMaxHeight(0.8f),
        )
        CircleIconButton(
            onClick = { onEvent(CreatorContract.Event.PlayCompilation) },
            contentDescription = "Play",
            imageVector = Icons.Filled.PlayArrow,
            modifier = Modifier.fillMaxHeight(),
        )
        CircleIconButton(
            onClick = { onEvent(CreatorContract.Event.ShareCompilation) },
            contentDescription = "Share",
            imageVector = Icons.Filled.Share,
            modifier = Modifier.fillMaxHeight(0.8f),
        )
        Box(modifier = Modifier.aspectRatio(1f)) {
            AnimatedContent(
                targetState = showCreateButton,
                label = "Create Button",
            ) {
                if (it) {
                    CircleIconButton(
                        onClick = { onEvent(CreatorContract.Event.StartSettingName) },
                        contentDescription = "Create",
                        imageVector = Icons.Filled.Check,
                        modifier = Modifier
                            .fillMaxHeight()
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "Dialog Bounds"),
                                animatedVisibilityScope = this@AnimatedVisibilityScope,
                            )
                            .animateEnterExit()
                    )
                }
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
