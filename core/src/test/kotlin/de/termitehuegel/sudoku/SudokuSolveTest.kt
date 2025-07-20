package de.termitehuegel.sudoku

import kotlin.test.Test
import kotlin.test.assertEquals

class SudokuSolveTest {

    @Test
    fun solvable() {
        val sudoku = Sudoku(
            listOf(
                listOf(null, 2, 4, 3),
                listOf(3, null, 2, 1),
                listOf(4, 1, null, 2),
                listOf(null, 3, 1, null),
            )
        ).solve()

        assertEquals(1, sudoku.field[0][0])
        assertEquals(4, sudoku.field[1][1])
        assertEquals(3, sudoku.field[2][2])
        assertEquals(2, sudoku.field[3][0])
        assertEquals(4, sudoku.field[3][3])
    }

    @Test
    fun emptySudoku() {
        Sudoku(
            listOf(
                listOf(null, null, null, null),
                listOf(null, null, null, null),
                listOf(null, null, null, null),
                listOf(null, null, null, null),
            )
        ).solve()
    }
}