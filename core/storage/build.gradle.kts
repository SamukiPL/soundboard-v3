import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("me.samuki.library")
    id("me.samuki.hilt")
    id("com.google.devtools.ksp")
    id("app.cash.sqldelight")
}

android {
    namespace = "me.samuki.core.storage"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("me.samuki.core.storage")
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.sqldelight.android.driver)
    implementation(libs.sqldelight.coroutines.extensions)

    testImplementation(project(":core:testing"))
}

//SQLDelight + Hilt workaround
androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val variantName = variant.name.replaceFirstChar { it.uppercaseChar() }
            tasks.getByName<KotlinCompile>("ksp${variantName}Kotlin") {
                setSource(tasks.getByName("generate${variantName}DatabaseInterface").outputs)
            }
        }
    }
}
