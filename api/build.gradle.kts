plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
}

dependencies {
    implementation(libs.guava)
    implementation(libs.gson)
    implementation(libs.spring.dotenv)
    implementation(libs.spring.web)
    implementation(libs.spring.security)
    implementation(libs.spring.mongodb)
    implementation(libs.spring.cache)
    implementation(libs.caffeine)
    implementation(project(":common"))
    testImplementation(libs.spring.test)
}

tasks {
    jar {
        archiveFileName.set("${project.name}-api-${project.version}.jar")
    }
}
