import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import me.samuki.buildlogic.config.AndroidVersions
import me.samuki.buildlogic.utils.configureEnvironmentFlavors
import me.samuki.buildlogic.utils.configureKotlinAndroid
import me.samuki.buildlogic.utils.ignoreVariants
import me.samuki.buildlogic.utils.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

@Suppress("unused")
class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                compileSdk = AndroidVersions.compileSdk
                defaultConfig {
                    targetSdk = AndroidVersions.targetSdk

                    buildTypes.getByName("debug") {
                        enableUnitTestCoverage = true
                    }
                }

                applicationVariants.all {
                    addJavaSourceFoldersToModel(
                        File(buildDir, "generated/ksp/$name/kotlin")
                    )
                }
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                ignoreVariants(this)
            }

            configureAppDependencies()
        }
    }

    private fun Project.configureAppDependencies() {
        dependencies {
            rootProject.allprojects
                .filter { it.childProjects.isEmpty() && it != this@configureAppDependencies && it.name != "testing" }
                .forEach { implementation(it) }
        }
    }
}
