plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
}

android {
    namespace = "me.samuki.core.model"
}

dependencies {
    implementation(project(":core:common"))
}
