package me.samuki.buildlogic.utils

import com.android.build.api.dsl.CommonExtension
import me.samuki.buildlogic.config.AndroidVersions
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        kotlinExtension.apply {
            explicitApi()
        }

        compileSdk = AndroidVersions.compileSdk

        defaultConfig {
            minSdk = AndroidVersions.minSdk
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }

        kotlinOptions {
            allWarningsAsErrors = true

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
//                "-opt-in=kotlin.Experimental",
                // Enable experimental kotlinx serialization APIs
//                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
                "-Xcontext-receivers"
            )

            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
