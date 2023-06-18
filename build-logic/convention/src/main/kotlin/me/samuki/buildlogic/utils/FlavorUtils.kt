package me.samuki.buildlogic.utils

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension

enum class Dimension {
    environment
}

enum class Flavor(val dimension: Dimension, val idSuffix: String? = null) {
    mock(dimension = Dimension.environment, "mock"),
    prod(dimension = Dimension.environment)
}

fun configureFlavors(
    extension: CommonExtension<*, *, *, *>,
) {
    extension.apply {
        productFlavors {
            flavorDimensions += Dimension.values().map { it.name }
            Flavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.idSuffix != null) {
                            this.applicationIdSuffix = it.idSuffix
                        }
                    }
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
            it.enable = it.buildType != "release" || it.flavorName != Flavor.mock.name
        }
    }
}
