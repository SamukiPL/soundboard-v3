package layers

import FeaturePlugin
import HiltPlugin
import LibraryPlugin
import me.samuki.buildlogic.utils.api
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DomainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(LibraryPlugin::class.java)
                apply(FeaturePlugin::class.java)
                apply(HiltPlugin::class.java)
            }

            dependencies {
                api(rootProject.project("core:domain"))
            }
        }
    }
}
