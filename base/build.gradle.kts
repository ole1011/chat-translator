dependencies {
    implementation(libs.guice)
    implementation(libs.deepl)
    implementation(libs.tinylog.api)
    implementation(libs.tinylog.impl)
    implementation(libs.tinylog.slf4j)
    implementation(libs.minestom)
    implementation(libs.dotenv)
    implementation(libs.classindex)
    implementation(project(":common"))
}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")
}
