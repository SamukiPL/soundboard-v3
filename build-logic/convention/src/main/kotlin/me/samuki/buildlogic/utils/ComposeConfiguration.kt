package me.samuki.buildlogic.utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures.compose = true
    }

    dependencies {
        implementation(libs.findLibrary("androidx-compose-bom").get())
        implementation(libs.findLibrary("androidx-compose-foundation").get())
        implementation(libs.findLibrary("androidx-compose-foundation-layout").get())
        implementation(libs.findLibrary("androidx-compose-material-icons-extended").get())
        implementation(libs.findLibrary("androidx-compose-material3").get())
        implementation(libs.findLibrary("androidx-compose-runtime").get())
        implementation(libs.findLibrary("androidx-compose-runtime-livedata").get())
        implementation(libs.findLibrary("androidx-compose-ui").get())
        implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
        implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
        debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
    }
}
