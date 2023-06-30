plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.feature.list"
}

dependencies {
    implementation(libs.androidx.compose.destinations.core)
    ksp(libs.androidx.compose.destinations.ksp)

    testImplementation(project(":core:testing"))
}
