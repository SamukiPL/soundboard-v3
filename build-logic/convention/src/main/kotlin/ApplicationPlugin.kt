import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import me.samuki.buildlogic.utils.configureKotlinAndroid
import me.samuki.buildlogic.utils.ignoreVariants
import me.samuki.buildlogic.utils.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                compileSdk = 33
                defaultConfig {
                    targetSdk = 33

                    buildTypes.getByName("debug") {
                        enableUnitTestCoverage = true
                    }
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
