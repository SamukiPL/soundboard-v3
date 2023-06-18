plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
}

android {
    namespace = "me.samuki.travel.common"
}

dependencies {
    api(libs.kotlinx.datetime)
    api(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
}
