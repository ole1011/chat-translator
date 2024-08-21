plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(rootProject.libs.guava)
    implementation(rootProject.libs.gson)
    implementation(rootProject.libs.spring.dotenv)
    implementation(rootProject.libs.spring.web)
    implementation(rootProject.libs.spring.security)
    implementation(rootProject.libs.spring.mongodb)
    implementation(rootProject.libs.spring.cache)
    implementation(rootProject.libs.caffeine)
    implementation(project(":common"))
    testImplementation(rootProject.libs.spring.test)
}

tasks {
    jar {
        archiveFileName.set("${project.name}-api-${project.version}.jar")
    }
}
