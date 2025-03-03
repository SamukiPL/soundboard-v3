package me.samuki.feature.promotion.list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.samuki.feature.promotion.PromotionContract
import me.samuki.feature.promotion.preview.PreviewPromotionProvider

@Composable
internal fun PromotionView(
    item: PromotionItem,
    onEvent: (PromotionContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .shadow(1.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onEvent(PromotionContract.Event.ShowPromotedSoundboard(item.url))
            }
            .padding(vertical = 4.dp)
    ) {
        AsyncImage(
            model = item.image.value,
            contentDescription = "TODO Soundboard Logo",
            modifier = Modifier.size(48.dp),
        )
        Text(
            text = item.name.value,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .animateContentSize()
                .weight(1f)
        )
    }
}

@Preview
@Composable
private fun PromotionViewPreview(
    @PreviewParameter(
        PreviewPromotionProvider::class,
        limit = 1
    ) promotion: PromotionItem
) = PromotionView(
    item = promotion,
    onEvent = { }
)
