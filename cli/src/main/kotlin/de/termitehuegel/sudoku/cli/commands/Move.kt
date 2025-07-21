package de.termitehuegel.sudoku.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import de.termitehuegel.sudoku.Sudoku
import kotlinx.serialization.json.Json
import java.io.File

class Move : CliktCommand() {

    val sudoku: Sudoku by argument("Input", help = "The path to the sudoku file.")
        .file(true, canBeDir = false)
        .convert { Json.decodeFromString(it.readText()) }

    val output: File by argument("Output", help = "The path to the sudoku file.")
        .file(false)

    val x: Int by option("-x", "--x", help = "The x coordinate of tile.").int().required()

    val y: Int by option("-y", "--y", help = "The y coordinate of tile.").int().required()

    val value: Int by option("-v", "--value", help = "The value to set.").int().required()

    override fun help(context: Context): String = "Makes a move."

    override fun run() = output.writeText(Json.encodeToString(sudoku.move(Sudoku.Tile(x, y), value)))
}