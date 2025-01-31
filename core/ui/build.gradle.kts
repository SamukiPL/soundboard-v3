plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("me.samuki.library.compose")
}

android {
    namespace = "me.samuki.core.ui"
}

dependencies {
    implementation(project(":core:model"))
}

