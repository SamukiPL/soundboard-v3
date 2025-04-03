package me.samuki.compilation.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.repeatable.SimpleToolbar
import me.samuki.core.ui.value.Label
import me.samuki.model.util.EMPTY_STRING

@Composable
public fun CompilationCreationSuccessScreen(navigation: CompilationCreationSuccessNavigation) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SimpleToolbar(
            title = Label(EMPTY_STRING),
            goBack = navigation::goToList,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center),
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .size(72.dp),
            )
            Text(
                text = "Compilation creation was successful\nDon't know what to say more.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Button(
            onClick = navigation::goToList,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Return to list",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
private fun CompilationCreationSuccessScreenPreview() = Surface {
    CompilationCreationSuccessScreen(
        object : CompilationCreationSuccessNavigation {
            override fun goToList() {
                /* no-op */
            }
        }
    )
}
