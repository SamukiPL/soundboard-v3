plugins {
    id("me.samuki.layers.data")
}

android {
    namespace = "me.samuki.feature.compilation.data"
}

dependencies {
    testImplementation(project(":core:testing"))
}
