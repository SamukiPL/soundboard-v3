package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.model.values.Name

@Composable
internal fun CompilationCreatorNameField(
    name: Name,
    showNamePlaceholder: ShowNamePlaceholder,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember {
        FocusRequester()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            BasicTextField(
                value = name.value,
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
                visible = showNamePlaceholder,
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

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun CompilationCreatorNameFieldPreview() {
    Surface {
        CompilationCreatorNameField(
            Name("New compilation"),
            false,
            {},
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}
