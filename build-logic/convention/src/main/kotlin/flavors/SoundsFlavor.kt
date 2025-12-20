package flavors

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import me.samuki.buildlogic.utils.configureEnvironmentFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class SoundsFlavor : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.findByType<LibraryExtension>()?.run {
            configureEnvironmentFlavors(this)
        }
        extensions.findByType<BaseAppModuleExtension>()?.run {
            configureEnvironmentFlavors(this) {
                defaultConfig.applicationId = it
            }
        }
    } ?: Unit
}
