plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
}

android {
    namespace = "me.samuki.core.testing"
}

dependencies {
    api(libs.kotest.runner)
    api(libs.kotest.assertions.core)
    api(libs.kotest.property)

    api(libs.mockk)
}
