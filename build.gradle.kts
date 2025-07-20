plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0" apply false
}

allprojects {
    group = "de.termitehuegel"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    dependencies {
        testImplementation(kotlin("test"))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin {
        jvmToolchain(21)
    }
}

