plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.feature.compilation.success"
}

dependencies {
    testImplementation(project(":core:testing"))
}
