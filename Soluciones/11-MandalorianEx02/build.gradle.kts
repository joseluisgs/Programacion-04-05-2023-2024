plugins {
    kotlin("jvm") version "1.9.20"
    application
    // dokka
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "dev.joseluisgs"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Las dependencias de log1
    implementation("org.lighthousegames:logging-jvm:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    configurations["compileClasspath"].forEach { file ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}