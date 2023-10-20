package me.samuki.domain.lint

@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR, message = "This is an internal domain logic." +
            "Should not be used from outside of domain module. " +
            "No compatibility guarantees are provided. "
)
internal annotation class InternalDomainApi
