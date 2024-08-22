plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(rootProject.libs.gson)
    implementation(rootProject.libs.spring.mongodb)
    implementation(rootProject.libs.minestom)
}
