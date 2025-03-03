package me.samuki.feature.promotion

import me.samuki.model.values.SoundboardUrl

public interface PromotionNavigation {

    public fun goBackToList()

    public fun goToSoundboard(url: SoundboardUrl)
}
