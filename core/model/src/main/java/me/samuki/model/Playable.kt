package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.Id
import me.samuki.model.values.Name

@Serializable
public sealed interface Playable {

    public val id: Id
    public val name: Name
}
