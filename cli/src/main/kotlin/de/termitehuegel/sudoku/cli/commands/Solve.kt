package de.termitehuegel.sudoku.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.types.file
import de.termitehuegel.sudoku.Sudoku
import kotlinx.serialization.json.Json
import java.io.File

class Solve : CliktCommand() {

    val sudoku: Sudoku by argument("Input", help = "The path to the sudoku file that should be solved.")
        .file(true, canBeDir = false)
        .convert { Json.decodeFromString(it.readText()) }

    val output: File by argument("Output", help = "The path where the sudoku should be saved.")
        .file(false)

    override fun help(context: Context): String = "Solves a sudoku."

    override fun run() = output.writeText(Json.encodeToString(sudoku.solve()))
}