package de.termitehuegel.sudoku

import kotlin.test.Test
import kotlin.test.assertEquals

class SudokuTest {

    @Test
    fun size() {
        val sudoku = Sudoku(
            listOf(
                listOf(1, 2, 4, 3),
                listOf(3, 4, 2, 1),
                listOf(4, 1, 3, 2),
                listOf(2, 3, 1, 4),
            )
        )
        assertEquals(4, sudoku.size)
    }

    @Test
    fun squareSize() {
        val sudoku = Sudoku(
            listOf(
                listOf(1, 2, 4, 3),
                listOf(3, 4, 2, 1),
                listOf(4, 1, 3, 2),
                listOf(2, 3, 1, 4),
            )
        )
        assertEquals(2, sudoku.squareSize)
    }
}