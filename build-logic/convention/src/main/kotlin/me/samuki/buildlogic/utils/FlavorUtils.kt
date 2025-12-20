package me.samuki.buildlogic.utils

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

enum class Dimension {
    SOUNDS;

    val lowerCaseName: String get() = name.lowercase()
}

enum class Flavor(val dimension: Dimension, val applicationId: String) {
    BAJOJAJO(Dimension.SOUNDS, "samuki.me.bajojajosoundboard"),
    ELEVEN(Dimension.SOUNDS, "samuki.me.elevensoundboard"),
    GIMPER(Dimension.SOUNDS, "samuki.me.gimpersoundboard"),
    GONCIARZ(Dimension.SOUNDS, "samuki.me.gonciarzsoundboard"),
    HONDA(Dimension.SOUNDS, "samuki.me.hondasoundboard"),
    JABLONOWSKI(Dimension.SOUNDS, "samuki.me.jablonowskisoundboard"),
    JANUSZ(Dimension.SOUNDS, "samuki.me.januszsoundboard"),
    KLOCUCH(Dimension.SOUNDS, "samuki.me.klocuchsoundboard"),
    KONON(Dimension.SOUNDS, "samuki.me.kononsoundboard"),
    KORWIN(Dimension.SOUNDS, "samuki.me.korwinsoundboard"),
    MAJOR(Dimension.SOUNDS, "samuki.me.majorsoundboard"),
    MAKLOWICZ(Dimension.SOUNDS, "samuki.me.maklowiczsoundboard"),
    MEMY(Dimension.SOUNDS, "samuki.me.memysoundboard"),
    OWSIAK(Dimension.SOUNDS, "samuki.me.owsiaksoundboard"),
    PAWEL(Dimension.SOUNDS, "samuki.me.pawelsoundboard"),
    PEWDIEPIE(Dimension.SOUNDS, "samuki.me.pewdiepiesoundboard"),
    POZIOMKA(Dimension.SOUNDS, "samuki.me.poziomkasoundboard"),
    STACHU(Dimension.SOUNDS, "samuki.me.stachusoundboard"),
    STONOGA(Dimension.SOUNDS, "samuki.me.stonogasoundboard"),
    STRYTA(Dimension.SOUNDS, "samuki.me.styrtasoundboard"),
    VIRONTESTO(Dimension.SOUNDS, "samuki.me.testovironsoundboard");

    val lowerCaseName: String
        get() = name.lowercase()
}


fun configureEnvironmentFlavors(
    extension: CommonExtension<*, *, *, *>,
    setApplicationId: ((String) -> Unit)? = null
) {
    extension.apply {
        productFlavors {
            flavorDimensions += Dimension.values().map { it.lowerCaseName }
            Flavor.values()
                .filter { it.dimension == Dimension.SOUNDS }
                .forEach { flavor ->
                    create(flavor.lowerCaseName) {
                        dimension = flavor.dimension.lowerCaseName
                        setApplicationId?.invoke(flavor.applicationId)
                    }
                }
        }
    }
}
