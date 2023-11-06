package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Supplement

@Serializable
public sealed interface Playable {

    public val id: Id
    public val supplement: Supplement
    public val name: Name
}
