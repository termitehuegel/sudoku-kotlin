plugins {
    application
}

dependencies {
    implementation(project(":core"))
    implementation("com.github.ajalt.clikt:clikt:5.0.3")
}

application {
    mainClass = "de.termitehuegel.sudoku.cli.CliApplicationKt"
}