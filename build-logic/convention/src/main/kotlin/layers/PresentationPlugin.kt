package layers

import FeaturePlugin
import HiltComposePlugin
import HiltPlugin
import LibraryComposePlugin
import LibraryPlugin
import me.samuki.buildlogic.utils.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PresentationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            with(pluginManager) {
                apply(LibraryPlugin::class.java)
                apply(LibraryComposePlugin::class.java)
                apply(FeaturePlugin::class.java)
                apply(HiltPlugin::class.java)
                apply(HiltComposePlugin::class.java)
            }

            dependencies {
                implementation(rootProject.project("core:presentation"))
                implementation(rootProject.project("core:ui"))
            }
            implementDomainLayer()
        }
    }
}
