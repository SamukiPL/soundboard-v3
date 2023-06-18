import com.android.build.gradle.LibraryExtension
import me.samuki.buildlogic.utils.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
        }
    }
}
