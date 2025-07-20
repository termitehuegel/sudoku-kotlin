package de.termitehuegel.sudoku.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import de.termitehuegel.sudoku.Sudoku
import kotlinx.serialization.json.Json
import java.io.File

class Empty : CliktCommand() {

    val size: Int by option("-s", "--size", help = "The size of the generated sudoku.")
        .int()
        .default(9)

    val output: File by argument("Output", help = "The path where the sudoku should be saved.")
        .file(false)

    override fun help(context: Context): String = "Generates an empty sudoku."

    override val autoCompleteEnvvar: String?
        get() = super.autoCompleteEnvvar

    override fun run() {
        output.writeText(Json.encodeToString(Sudoku.empty(size)))
    }
}