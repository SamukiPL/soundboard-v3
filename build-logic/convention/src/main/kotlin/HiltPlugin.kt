import me.samuki.buildlogic.utils.implementation
import me.samuki.buildlogic.utils.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                implementation(libs.findLibrary("hilt-android").get())
                ksp(libs.findLibrary("hilt-android-compiler").get())
            }
        }
    }
}
