plugins {
    id("me.samuki.layers.domain")
}

android {
    namespace = "me.samuki.feature.compilation.domain"
}

dependencies {
    testImplementation(project(":core:testing"))
}
