plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
}

android {
    namespace = "me.samuki.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    api(project(":core:model"))

    testImplementation(project(":core:testing"))
}
