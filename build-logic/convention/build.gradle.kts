plugins {
    `kotlin-dsl`
}

group = "me.samuki.travel.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.0.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation(kotlin("script-runtime"))
}
gradlePlugin {
    plugins {
        register("applicationPlugin") {
            id = "me.samuki.application"
            implementationClass = "ApplicationPlugin"
        }
        register("applicationPluginCompose") {
            id = "me.samuki.application.compose"
            implementationClass = "ApplicationComposePlugin"
        }
        register("libraryPlugin") {
            id = "me.samuki.library"
            implementationClass = "LibraryPlugin"
        }
        register("libraryPluginCompose") {
            id = "me.samuki.library.compose"
            implementationClass = "LibraryComposePlugin"
        }
        register("hiltPlugin") {
            id = "me.samuki.hilt"
            implementationClass = "HiltPlugin"
        }
        register("hiltComposePlugin") {
            id = "me.samuki.hilt.compose"
            implementationClass = "HiltComposePlugin"
        }

        register("featurePlugin") {
            id = "me.samuki.feature"
            implementationClass = "FeaturePlugin"
        }
        register("dataPlugin") {
            id = "me.samuki.layers.data"
            implementationClass = "layers.DataPlugin"
        }
        register("domainPlugin") {
            id = "me.samuki.layers.domain"
            implementationClass = "layers.DomainPlugin"
        }
        register("presentationPlugin") {
            id = "me.samuki.layers.presentation"
            implementationClass = "layers.PresentationPlugin"
        }
    }
}
