plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.feature.promotion"
}

dependencies {
    implementation(libs.coil)

    testImplementation(project(":core:testing"))
}
