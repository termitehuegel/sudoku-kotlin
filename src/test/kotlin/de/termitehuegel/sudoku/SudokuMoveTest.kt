package de.termitehuegel.sudoku

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class SudokuMoveTest {

    @Test
    fun tileOutOfBounds() {
        val sudoku = Sudoku(listOf(
            listOf(null, 2, 4, 3),
            listOf(3, 4, 2, 1),
            listOf(4, 1, 3, 2),
            listOf(2, 3, 1, 4),
        ))

        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(-1, 1), 1) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(4, 4), 1) }
    }

    @Test
    fun valueOutOfBounds() {
        val sudoku = Sudoku(listOf(
            listOf(null, 2, 4, 3),
            listOf(3, 4, 2, 1),
            listOf(4, 1, 3, 2),
            listOf(2, 3, 1, 4),
        ))

        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 0) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 5) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 200) }
    }

    @Test
    fun tileAlreadyFilled() {
        val sudoku = Sudoku(listOf(
            listOf(null, 2, 4, 3),
            listOf(3, 4, 2, 1),
            listOf(4, 1, 3, 2),
            listOf(2, 3, 1, 4),
        ))

        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(1, 1), 2) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(3, 3), 4) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 1), 1) }
    }

    @Test
    fun invalid() {
        val sudoku = Sudoku(listOf(
            listOf(null, 2, 4, 3),
            listOf(3, 4, 2, 1),
            listOf(4, 1, 3, 2),
            listOf(2, 3, 1, 4),
        ))

        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 2) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 3) }
        assertFailsWith<IllegalArgumentException> { sudoku.move(Sudoku.Tile(0, 0), 4) }
    }

    @Test
    fun valid() {
        val sudoku = Sudoku(listOf(
            listOf(null, 2, 4, 3),
            listOf(3, 4, 2, 1),
            listOf(4, 1, 3, 2),
            listOf(2, 3, 1, 4),
        ))

        val newSudoku = sudoku.move(Sudoku.Tile(0, 0), 1)

        assertEquals(1, newSudoku.field[0][0])
        assertNull(sudoku.field[0][0])
    }
}