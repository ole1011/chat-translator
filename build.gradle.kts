import org.gradle.api.JavaVersion.VERSION_21

group = "de.ole101.translator"
version = "1.0-SNAPSHOT"
description = "A Minestom server implementation with an automatic chat translator for Minecraft"

java.sourceCompatibility = VERSION_21

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.google.inject:guice:7.0.0")
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("com.deepl.api:deepl-java:1.5.0")
    implementation("org.tinylog:tinylog-api:2.7.0")
    implementation("org.tinylog:tinylog-impl:2.7.0")
    implementation("org.tinylog:slf4j-tinylog:2.7.0")
    implementation("net.minestom:minestom-snapshots:7b180172ce")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Javadoc> {
        options.encoding = "UTF-8"
    }

    withType<Jar> {
        manifest {
            attributes["Main-Class"] = "de.ole101.translator.TranslatorPlugin"
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
