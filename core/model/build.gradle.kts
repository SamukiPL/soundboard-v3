import me.samuki.buildlogic.utils.implementation

plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "me.samuki.core.model"
}

dependencies {
    implementation(project(":core:common"))

    api(libs.kotlinx.serialization.json)
}
