package me.samuki.model.values


sealed interface Query {

    object Empty : Query

    @JvmInline
    value class Text(
        val value: String
    ): Query
}
