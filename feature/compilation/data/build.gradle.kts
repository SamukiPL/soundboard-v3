plugins {
    id("me.samuki.layers.data")
    id("me.samuki.flavor.environment")
}

android {
    namespace = "me.samuki.feature.compilation.data"
}

dependencies {
    testImplementation(project(":core:testing"))
}
