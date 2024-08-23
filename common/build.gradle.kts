plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
}

dependencies {
    implementation(rootProject.libs.gson)
    implementation(rootProject.libs.spring.mongodb)
    implementation(rootProject.libs.minestom)
}
