plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("me.samuki.library.compose")
}

android {
    namespace = "me.samuki.core.presentation"
}

dependencies {
    implementation(libs.androidx.compose.destinations.core)
    ksp(libs.androidx.compose.destinations.ksp)
}
