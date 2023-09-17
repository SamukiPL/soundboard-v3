package me.samuki.model.values

import android.net.Uri

//TODO hilt doesn't like value classes, check why
public data class Path(
    public val value: Uri
)
