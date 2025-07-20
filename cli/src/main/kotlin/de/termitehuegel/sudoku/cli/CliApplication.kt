package de.termitehuegel.sudoku.cli

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import de.termitehuegel.sudoku.cli.commands.Empty
import de.termitehuegel.sudoku.cli.commands.Print
import de.termitehuegel.sudoku.cli.commands.Solve
import de.termitehuegel.sudoku.cli.commands.SudokuCli


fun main(args: Array<String>) = SudokuCli()
    .subcommands(Empty(), Solve(), Print())
    .main(args)
