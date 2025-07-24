pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TinhTX Player"
include(
    ":app",
    ":core",
    ":domain",
    ":data",
    ":feature_home",
    ":feature_player",
    ":feature_collection",
    ":feature_settings",
    ":feature_pip",
    ":feature_equalizer"
)