@file:Suppress("UnstableApiUsage")

include(":benchmark")


include(":app:benchmark")


pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
  }
}
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
  }
}

rootProject.name = "Pico"
include(":app",":pico",":benchmark")
