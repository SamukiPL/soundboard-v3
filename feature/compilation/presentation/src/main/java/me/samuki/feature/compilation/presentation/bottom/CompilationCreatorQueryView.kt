@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.bottom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.QueryViewState
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.R
import me.samuki.feature.list.toolbar.QueryView

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorQueryView(
    queryViewState: QueryViewState,
    onEvent: (CreatorContract.Event) -> Unit,
    changeBar: () -> Unit,
    modifier: Modifier = Modifier,
) = with(this@SharedTransitionScope) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        IconButton(
            onClick = changeBar,
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "left"),
                    animatedVisibilityScope = this@AnimatedVisibilityScope,
                )
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add_pause),
                contentDescription = "Accept",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        QueryView(
            state = queryViewState,
            onQueryChange = {
                onEvent(
                    CreatorContract.Event.SetQuery(it)
                )
            },
            removeQuery = {
                onEvent(
                    CreatorContract.Event.RemoveQuery
                )
            },
        )
    }
}

@Composable
@Preview
private fun CompilationCreatorQueryViewPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(true) {
            CompilationCreatorQueryView(
                QueryViewState(),
                {},
                {},
                modifier = Modifier.height(48.dp)
            )
        }
    }
}
