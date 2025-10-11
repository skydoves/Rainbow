pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
  }
}
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
  }
}
rootProject.name=("RainbowDemo")
include(":app")
include(":rainbow")
