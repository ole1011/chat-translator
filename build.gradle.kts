import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    alias(libs.plugins.shadow)
}

allprojects {
    apply(plugin = "java-library")

    group = "de.ole101.translator"
    version = "1.0-SNAPSHOT"
    description = "A Minestom server implementation with an automatic chat translator for Minecraft"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io")
    }

    dependencies {
        annotationProcessor(rootProject.libs.bundles.utils)
        implementation(rootProject.libs.bundles.utils)
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = VERSION_21.toString()
        targetCompatibility = VERSION_21.toString()
        options.encoding = "UTF-8"
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":api"))
    implementation(project(":common"))
}

tasks {
    test {
        useJUnitPlatform()
    }

    named<Jar>("jar") {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    named<ShadowJar>("shadowJar") {
        archiveFileName.set("${project.name}-${project.version}-all.jar")
    }

    build {
        dependsOn(named("shadowJar"))
    }
}
