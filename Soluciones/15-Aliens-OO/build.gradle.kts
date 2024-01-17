plugins {
    kotlin("jvm") version "1.9.20"
    application
    // para crear la doc con dokka
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    // incluimos todo como dependencias zip
    configurations["compileClasspath"].forEach { file ->
        from(zipTree(file.absolutePath))
    }
}