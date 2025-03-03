package me.samuki.feature.promotion.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.feature.promotion.list.PromotionItem
import me.samuki.model.values.ImageUrl
import me.samuki.model.values.Name
import me.samuki.model.values.StoreUrl

internal class PreviewPromotionProvider : PreviewParameterProvider<PromotionItem> {

    override val values: Sequence<PromotionItem>
        get() = sequenceOf(
            PromotionItem(
                fullName = Name("Preview Soundboard"),
                image = ImageUrl("Url"),
                name = Name("Preview"),
                url = StoreUrl("Url")
            )
        )
}
