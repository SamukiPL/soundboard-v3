package me.samuki.feature.promotion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.promotion.R

@Composable
internal fun OfflineLabel(
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier
        .background(MaterialTheme.colorScheme.errorContainer)
        .fillMaxWidth()
        .padding(4.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    Text(stringResource(R.string.offline_text))
}

@Preview
@Composable
private fun OfflineLabelPreview() = Surface {
    OfflineLabel()
}
