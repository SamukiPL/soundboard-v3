package layers

import me.samuki.buildlogic.utils.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private const val DOMAIN_NAME = "domain"
private const val CORE_DOMAIN_NAME = "core:domain"

fun Project.implementDomainLayer() {
    dependencies {
        parent!!.findProject(DOMAIN_NAME)?.let { domain ->
            implementation(domain)
        } ?: rootProject.findProject(CORE_DOMAIN_NAME)?.let { coreDomain ->
            implementation(coreDomain)
        } ?: throw IllegalStateException("You must have a \"domain\" layer in :core module or inside feature")
    }
}
