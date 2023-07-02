plugins {
    id("me.samuki.layers.presentation")
}

android {
    namespace = "me.samuki.feature.list"
}

dependencies {
    testImplementation(project(":core:testing"))
}
