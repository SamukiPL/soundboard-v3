plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "me.samuki.core.model"
}

dependencies {
    api(libs.kotlinx.serialization.json)
}
