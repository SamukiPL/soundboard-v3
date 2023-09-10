import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import me.samuki.buildlogic.config.AndroidVersions
import me.samuki.buildlogic.utils.configureKotlinAndroid
import me.samuki.buildlogic.utils.ignoreVariants
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.apply {
                    targetSdk = AndroidVersions.targetSdk
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFile("consumer-rules.pro")
                    buildTypes.getByName("debug") {
                        enableUnitTestCoverage = true
                    }
                }

                libraryVariants.all {
                    addJavaSourceFoldersToModel(
                        File(buildDir, "generated/ksp/$name/kotlin")
                    )
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                ignoreVariants(this)
            }
        }
    }

}
