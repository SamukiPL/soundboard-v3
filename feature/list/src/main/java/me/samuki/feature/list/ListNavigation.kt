package me.samuki.feature.list

import me.samuki.domain.params.SetType
import me.samuki.model.Playable

public interface ListNavigation {

    public fun goToSettingsRationale(playable: Playable, setType: SetType)
}
