plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.navigation"
}

dependencies {
    implementation(project(":feature:list"))

    implementation(libs.androidx.compose.destinations.core)
    ksp(libs.androidx.compose.destinations.ksp)

    testImplementation(project(":core:testing"))
}
