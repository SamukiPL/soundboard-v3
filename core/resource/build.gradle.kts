plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
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
