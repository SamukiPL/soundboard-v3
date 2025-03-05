package me.samuki.feature.promotion

import me.samuki.model.values.StoreUrl

public interface PromotionNavigation {

    public fun goBackToList()

    public fun goToSoundboard(url: StoreUrl)
}
