@file:Suppress("UnstableApiUsage")

package me.samuki.buildlogic.utils

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension

enum class Dimension {
    ENVIRONMENT;

    val lowerCaseName: String get() = name.lowercase()
}

enum class Flavor(val dimension: Dimension) {
    MOCK(dimension = Dimension.ENVIRONMENT),
    PROD(dimension = Dimension.ENVIRONMENT);

    val lowerCaseName: String get() = name.lowercase()
}

fun configureEnvironmentFlavors(
    extension: CommonExtension<*, *, *, *>,
) {
    extension.apply {
        productFlavors {
            flavorDimensions += Dimension.values().map { it.lowerCaseName }
            Flavor.values()
                .filter { it.dimension == Dimension.ENVIRONMENT }
                .forEach { flavor ->
                    create(flavor.lowerCaseName) {
                        dimension = flavor.dimension.lowerCaseName
                    }
                }
        }
    }
}

fun ignoreVariants(
    extension: AndroidComponentsExtension<*, *, *>
) {
    extension.apply {
        beforeVariants {
            it.enable = it.buildType != "release" || it.flavorName != Flavor.MOCK.lowerCaseName
        }
    }
}
