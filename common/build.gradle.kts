plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
}

dependencies {
    implementation(libs.gson)
    implementation(libs.spring.mongodb)
    implementation(libs.minestom)
}
