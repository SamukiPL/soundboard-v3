plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("me.samuki.flavor.sounds")
    kotlin("plugin.serialization")
}

android {
    namespace = "me.samuki.core.resource"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.datastore.preferences)
}
