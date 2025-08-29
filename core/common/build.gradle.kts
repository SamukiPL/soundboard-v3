plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
}

android {
    namespace = "me.samuki.core.common"
}

dependencies {
    api(libs.kotlinx.datetime)
    api(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
}
