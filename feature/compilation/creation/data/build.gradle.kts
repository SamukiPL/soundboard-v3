plugins {
    id("me.samuki.layers.data")
    id("me.samuki.flavor.environment")
}

android {
    namespace = "me.samuki.feature.compilation.creation.data"
}

dependencies {
    testImplementation(project(":core:testing"))
}
