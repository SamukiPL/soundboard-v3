package layers

import me.samuki.buildlogic.utils.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

const val domainName = "domain"

fun Project.implementDomainLayer() {
    dependencies {
        parent!!.findProject(domainName)?.let { domain ->
            implementation(domain)
        } ?: throw IllegalStateException("You need to create module named \"domain\" inside your feature!")
    }
}
