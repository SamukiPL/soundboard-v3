plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.feature.compilation.presentation"
}

dependencies {
    testImplementation(project(":core:testing"))
}
