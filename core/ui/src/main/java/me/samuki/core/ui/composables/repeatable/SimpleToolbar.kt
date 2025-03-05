package me.samuki.core.ui.composables.repeatable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.samuki.core.ui.value.Label

@Composable
public fun SimpleToolbar(
    title: Label,
    goBack: (() -> Unit),
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        IconButton(
            onClick = goBack,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = title.value,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f),
        )
        Box(modifier = Modifier.size(48.dp))
    }
}

@Composable
@Preview
private fun SimpleToolbarPreview() {
    Surface {
        SimpleToolbar(
            title = Label("Simple Toolbar"),
            goBack = {}
        )
    }
}
