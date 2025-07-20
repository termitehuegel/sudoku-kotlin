package de.termitehuegel.sudoku

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SudokuTilesTest {

    @Test
    fun rangeUntil() {
        val range = Sudoku.Tile(0, 0)..<Sudoku.Tile(2, 2)
        assertContains(range, Sudoku.Tile(0, 0))
        assertContains(range, Sudoku.Tile(0, 1))
        assertContains(range, Sudoku.Tile(1, 0))
        assertContains(range, Sudoku.Tile(1, 1))
    }

    @Test
    fun emptyRange() {
        val range = Sudoku.Tile(1, 1)..<Sudoku.Tile(-2, 2)
        assertEquals(0, range.size)
    }

    @Test
    fun selfRange() {
        val range = Sudoku.Tile(1, 1)..<Sudoku.Tile(1, 1)
        assertEquals(0, range.size)
    }
}