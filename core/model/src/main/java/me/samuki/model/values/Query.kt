package me.samuki.model.values

import me.samuki.model.util.EMPTY_STRING


public sealed interface Query {

    public data object Empty : Query

    @JvmInline
    public value class Text(
        public val value: String
    ): Query
}

public fun Query.getQueryValue(): String = when (this) {
    Query.Empty -> EMPTY_STRING
    is Query.Text -> value
}
