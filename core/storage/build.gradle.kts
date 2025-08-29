import com.google.devtools.ksp.gradle.KspTaskJvm

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
plugins.withId("com.google.devtools.ksp") {
    plugins.withId("com.android.base") {
        androidComponents {
            onVariants { variant ->
                val cap = variant.name.replaceFirstChar(Char::uppercase)
                tasks.matching { it.name == "ksp${cap}Kotlin" }.configureEach {
                    dependsOn("generate${cap}DatabaseInterface")
                }
            }
        }
    }
}
