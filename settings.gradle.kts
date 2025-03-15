import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            url = uri("https://maven.pkg.github.com/u1tramarinet/AndroidCore")
            credentials {
                val localProperties = Properties()
                localProperties.load(file("local.properties").inputStream())

                username = localProperties.getProperty("gpr.user") ?: System.getenv("USERNAME")
                password = localProperties.getProperty("gpr.key") ?: System.getenv("TOKEN")
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

rootProject.name = "OrganizerApp"
include(":app")
 