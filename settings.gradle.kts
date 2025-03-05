pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "soundboard-v3"
include(":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":core:presentation")
include(":core:resource")
include(":core:storage")
include(":core:testing")
include(":core:ui")
include(":feature:compilation:data")
include(":feature:compilation:domain")
include(":feature:compilation:presentation")
include(":feature:list")
include(":feature:promotion")
include(":feature:rationale")
include(":navigation")
