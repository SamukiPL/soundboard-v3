package me.samuki.model.values

@JvmInline
value class Query(
    val value: String
) {
    val isEmpty get() = value.isEmpty()
}
