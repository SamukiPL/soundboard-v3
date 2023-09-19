package me.samuki.model.values

import android.net.Uri
import kotlinx.serialization.Serializable
import me.samuki.model.serialization.PathSerializer

//TODO hilt doesn't like value classes, check why
@Serializable(with = PathSerializer::class)
public data class Path(
    public val value: Uri
)
