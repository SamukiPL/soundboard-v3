import java.nio.charset.StandardCharsets
import java.util.Properties

plugins {
    id("me.samuki.application")
    id("me.samuki.application.compose")
    id("me.samuki.hilt")
    id("me.samuki.hilt.compose")
    id("me.samuki.flavor.sounds")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.play.publisher)
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.reader(StandardCharsets.UTF_8).use { reader ->
            load(reader)
        }
    }
}

android {
    namespace = "me.samuki.soundboard"

    signingConfigs {
        create("release") {
            localProperties.getProperty("keystore.path")?.let {
                storeFile = file(it)
            }
            storePassword = localProperties.getProperty("keystore.password")
            keyAlias = localProperties.getProperty("key.alias")
            keyPassword = localProperties.getProperty("key.password")
        }
    }

    defaultConfig {
        versionCode = 20251221
        versionName = "3.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

play {
    track = "beta"
    releaseStatus = com.github.triplet.gradle.androidpublisher.ReleaseStatus.COMPLETED
    defaultToAppBundles = true

    resolutionStrategy = com.github.triplet.gradle.androidpublisher.ResolutionStrategy.IGNORE

    serviceAccountCredentials = file("key.json")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.splashscreen)

    implementation(libs.androidx.compose.destinations.core)
    ksp(libs.androidx.compose.destinations.ksp)

    implementation(libs.settings.multiplatform)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
}
