package me.samuki.buildlogic.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.api(dependency: Any) {
    add("api", dependency)
}

fun DependencyHandlerScope.debugApi(dependency: String) {
    add("debugApi", dependency)
}

fun DependencyHandlerScope.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.kapt(dependency: Any) {
    add("kapt", dependency)
}
