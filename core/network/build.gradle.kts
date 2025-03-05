import me.samuki.buildlogic.utils.configureEnvironmentFlavors

plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("me.samuki.flavor.environment")
    kotlin("plugin.serialization")
}

android {
    namespace = "me.samuki.core.network"
}


dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
}
