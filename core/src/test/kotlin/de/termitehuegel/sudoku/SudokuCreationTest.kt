package de.termitehuegel.sudoku

import kotlin.test.Test
import kotlin.test.assertFailsWith

class SudokuCreationTest {

    @Test
    fun nonSquareField() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(null, null, null, null, null),
                    listOf(null, null, null, null),
                    listOf(null, null, null, null),
                )
            )
        }
    }

    @Test
    fun invalid() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(1, 2, 4, 3),
                    listOf(3, 4, 2, 4),
                    listOf(4, 1, 3, 2),
                    listOf(2, 3, 1, 4),
                )
            )
        }
    }

    @Test
    fun valueOutOfBounds() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(100, 2, 4, 3),
                    listOf(3, 4, 2, 1),
                    listOf(4, 1, 3, 2),
                    listOf(2, 3, 1, 4),
                )
            )
        }
    }

    @Test
    fun nonPerfectSquare() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(null, null, null, null, null),
                    listOf(null, null, null, null, null),
                    listOf(null, null, null, null, null),
                    listOf(null, null, null, null, null),
                    listOf(null, null, null, null, null),
                )
            )
        }
    }

    @Test
    fun valid() {
        Sudoku(
            listOf(
                listOf(1, 2, 4, 3),
                listOf(3, 4, 2, 1),
                listOf(4, 1, 3, 2),
                listOf(2, 3, 1, 4),
            )
        )
    }

    @Test
    fun validWithNull() {
        Sudoku(
            listOf(
                listOf(null, 2, 4, 3),
                listOf(3, null, 2, null),
                listOf(4, null, 3, 2),
                listOf(2, 3, 1, null),
            )
        )
    }

    @Test
    fun invalidWithNull() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(null, null, 4, 3),
                    listOf(null, null, 2, null),
                    listOf(4, null, 3, 2),
                    listOf(null, 3, 3, 4),
                )
            )
        }
    }

    @Test
    fun empty() {
        Sudoku.empty(4)
    }

    @Test
    fun emptyNonPerfectSquare() {
        assertFailsWith<IllegalArgumentException> { Sudoku.empty(5) }
    }

    @Test
    fun singleTile() {
        assertFailsWith<IllegalArgumentException> {
            Sudoku(
                listOf(
                    listOf(1),
                )
            )
        }
    }
}