package me.samuki.rationale.state.permission

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.repeatable.SimpleToolbar
import me.samuki.core.ui.value.Label
import me.samuki.feature.rationale.R
import me.samuki.rationale.RationaleContract

@Composable
internal fun RationalePermissionContent(
    onEvent: (RationaleContract.Event) -> Unit
) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SimpleToolbar(
            title = Label(""),
            goBack = { onEvent(RationaleContract.Event.GoBack) },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
        
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = stringResource(R.string.rationale_description),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
        
        Button(
            onClick = { onEvent(RationaleContract.Event.OpenSettings) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.rationale_button),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RationalePermissionContentPreview() {
    MaterialTheme {
        RationalePermissionContent(
            onEvent = {}
        )
    }
} 