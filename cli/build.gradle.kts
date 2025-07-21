plugins {
    application
}

dependencies {
    implementation(project(":core"))
    implementation(rootProject.libs.clikt)
}

application {
    mainClass = "de.termitehuegel.sudoku.cli.CliApplicationKt"
}