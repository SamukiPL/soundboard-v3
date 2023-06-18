import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import me.samuki.buildlogic.utils.configureKotlinAndroid
import me.samuki.buildlogic.utils.ignoreVariants
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.apply {
                    targetSdk = 33
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFile("consumer-rules.pro")
                    buildTypes.getByName("debug") {
                        enableUnitTestCoverage = true
                    }
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                ignoreVariants(this)
            }
        }
    }

}
