package de.termitehuegel.sudoku.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import de.termitehuegel.sudoku.Sudoku
import kotlinx.serialization.json.Json
import kotlin.math.log10

class Print : CliktCommand() {

    val sudoku: Sudoku by argument("Input", help = "The path to the sudoku file.")
        .file(true, canBeDir = false)
        .convert { Json.decodeFromString(it.readText()) }

    val onlyNumbers: Boolean by option("-n", "--only-numbers", help = "Outputs the sudoku only using numbers for the tiles.")
        .flag(default = false)

    val charset: List<Char> = listOf(
        ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9',  'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'O', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'
    )

    override fun help(context: Context): String = "Prints a sudoku to the stout."

    override fun run() {
        if (sudoku.size >= charset.size || onlyNumbers) printWithNumbers()
        else printWithCharset()
    }

    private fun printWithCharset() {
        val separationLine: String = ("+" + "-".repeat(2 * sudoku.squareSize + 1)).repeat(sudoku.squareSize) + "+"

        val body: String = sudoku.field.mapIndexed { y, row ->
            val string = "| ${
                row.mapIndexed { x, value ->
                    val string = charset[value ?: 0].toString()
                    if ((x + 1) % sudoku.squareSize == 0) "$string |" else string
                }.joinToString(" ")
            }"
            if ((y + 1) % sudoku.squareSize == 0) string + System.lineSeparator() + separationLine else string
        }.joinToString(System.lineSeparator())

        println(separationLine + System.lineSeparator() + body)
    }

    private fun printWithNumbers() {
        val space: Int = log10(sudoku.size.toDouble()).toInt() + 1
        val separationLine: String =
            ("+" + "-".repeat(sudoku.squareSize + 1 + space * sudoku.squareSize)).repeat(sudoku.squareSize) + "+"

        val body: String = sudoku.field.mapIndexed { y, row ->
            val string = "| ${
                row.mapIndexed { x, value ->
                    val string = (value?.toString() ?: " ").padStart(space)
                    if ((x + 1) % sudoku.squareSize == 0) "$string |" else string
                }.joinToString(" ")
            }"
            if ((y + 1) % sudoku.squareSize == 0) string + System.lineSeparator() + separationLine else string
        }.joinToString(System.lineSeparator())

        println(separationLine + System.lineSeparator() + body)
    }
}