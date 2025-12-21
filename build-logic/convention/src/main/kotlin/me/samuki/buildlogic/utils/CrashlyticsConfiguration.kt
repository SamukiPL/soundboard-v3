package me.samuki.buildlogic.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureCrashlytics() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        implementation(platform(libs.findLibrary("firebase-bom").get()))
        implementation(libs.findLibrary("firebase-analytics").get())
        implementation(libs.findLibrary("firebase-common").get())
        implementation(libs.findLibrary("firebase-crashlytics").get())
    }
}
