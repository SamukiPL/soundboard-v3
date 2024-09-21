@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)

package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Name

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorFinishDialog(
    combinables: List<CompilationCreatorItem>,
    state: FinishDialogState,
    onEvent: (CreatorContract.Event) -> Unit,
) {
    val focusRequester = remember {
        FocusRequester()
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = "Dialog Bounds"),
                animatedVisibilityScope = this@AnimatedVisibilityScope,
            )
            .animateEnterExit()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Last step",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        onEvent(CreatorContract.Event.GoBack)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Favourite",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                BasicTextField(
                    value = state.name.value,
                    onValueChange = { input ->
                        onEvent(
                            CreatorContract.Event.SetName(
                                name = Name(input)
                            )
                        )
                    },
                    textStyle = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .focusRequester(focusRequester)
                )
                androidx.compose.animation.AnimatedVisibility(
                    visible = state.showNamePlaceholder,
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        ),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "New compilation name...",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                itemsIndexed(
                    combinables,
                    key = { index, item -> item.id.toString() }) { index, item ->
                    when (val combinable = item.combinedCombinable.combinable) {
                        is Pause -> {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(8.dp),
                            ) {
                                Text(
                                    text = "${
                                        combinable.repeats.toBigDecimal().movePointLeft(1)
                                    }s",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.align(Alignment.Center),
                                )
                            }
                        }
                        is Sound -> {
                            Text(
                                text = combinable.name.value,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = CircleShape
                                    )
                                    .padding(8.dp),
                            )
                        }
                    }

                    if (combinables.lastIndex != index) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = state.finishDialogState,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxWidth(),
        ) {
            FloatingActionButton(
                onClick = {
                    onEvent(
                        CreatorContract.Event.EndCreation,
                    )
                },
                modifier = Modifier
            ) {
                Text(
                    text = "Finish",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun DialogPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(true) {
            CompilationCreatorFinishDialog(
                emptyList(),
                FinishDialogState()
            ) { }
        }
    }
}
