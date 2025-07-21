plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
    id("org.jetbrains.dokka") version "2.0.0"
    application
}

dependencies {
    implementation(project(":core"))
    implementation(rootProject.libs.clikt)
    implementation(rootProject.libs.kotlinx.serialization.json)
    testImplementation(kotlin("test"))
}

application {
    mainClass = "de.termitehuegel.sudoku.cli.CliApplicationKt"
    applicationName = "sudoku-kotlin-cli"
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}