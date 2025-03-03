package me.samuki.feature.promotion.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.promotion.PromotionContract
import me.samuki.feature.promotion.R

@Composable
internal fun PromotionErrorView(
    onEvent: (PromotionContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.error_body),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Button(
            onClick = { onEvent.invoke(PromotionContract.Event.ShowStoreProfile) },
            modifier = Modifier.padding(bottom = 8.dp),
        ) {
            Text(
                text = stringResource(R.string.error_button),
            )
        }
    }
}

@Preview
@Composable
private fun ErrorViewPreview() = Surface {
    PromotionErrorView({})
}
