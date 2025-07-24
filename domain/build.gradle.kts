plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation(libs.inject)
    // Coroutines for UseCases
    implementation(libs.kotlinx.coroutines.core)

    // JUnit for testing
    testImplementation(libs.junit)
}
