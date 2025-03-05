plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("me.samuki.flavor.environment")
}

android {
    namespace = "me.samuki.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:resource"))
    implementation(project(":core:storage"))
    implementation(project(":core:network"))
}
