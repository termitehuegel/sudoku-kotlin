package de.termitehuegel.sudoku

import de.termitehuegel.sudoku.exception.InvalidSudokuException
import kotlin.math.floor
import kotlin.math.sqrt


class Sudoku {

    val field: List<List<Int?>>

    val size
        get() = field.size

    val squareSize: Int

    /**
     * @throws IllegalArgumentException
     */
    constructor(field: List<List<Int?>>) {
        this.field = field

        require(field.all { it.size == field.size }) { "A sudoku field has to be square." }
        require(size > 1) { "Needs more than one tile per row." }
        require(field.all { row -> row.all { value -> value in 1..size || value == null } }) { "All values need to be in bounds." }

        val sq = sqrt(size.toDouble())
        this.squareSize = floor(sq).toInt()
        require(sq == squareSize.toDouble()) { "Size needs to be a perfect square." }

        val validation: Result<Unit> = runCatching { validate() }
        require(validation.isSuccess) { "The sudokus is invalid. Because: ${validation.exceptionOrNull()}" }
    }

    /**
     * @throws IllegalArgumentException
     */
    private constructor(sudoku: Sudoku, field: List<List<Int?>>, tile: Tile) {
        this.field = field
        this.squareSize = sudoku.squareSize

        val validation: Result<Unit> = runCatching { validateTile(tile) }
        require(validation.isSuccess) { "The sudokus is invalid. Because: ${validation.exceptionOrNull()}" }
    }


    /**
     * @throws IllegalArgumentException
     */
    fun move(tile: Tile, value: Int): Sudoku {
        require(value in (1..size)) { "The provided value is out of bounds for this sudoku." }
        require(tile.inBounds) { "The provided tile is out of bounds for this sudoku." }
        require(tile.value == null) { "The provided tile is already filled." }

        return Sudoku(
            sudoku = this,
            field = field.mapIndexed { y, row ->
                if (tile.y != y) row
                else row.mapIndexed { x, original ->
                    if (tile.x != x) original
                    else value
                }
            },
            tile = tile
        )
    }

    fun solve(): Sudoku {
        val tiles: List<Tile> = field.flatMapIndexed { y, row ->
            row.mapIndexed { x, value -> if (value == null) Tile(x, y) else null }.filterNotNull()
        }
        val tile: Tile = tiles.firstOrNull() ?: return this
        (1..size).forEach { value ->
            val sudoku: Result<Sudoku> = runCatching { move(tile, value).solve() }
            if (sudoku.isSuccess) {
                return sudoku.getOrThrow()
            }
        }
        throw InvalidSudokuException("There is no solution to this sudoku.")
    }

    /**
     * @throws InvalidSudokuException When the field is invalid.
     */
    private fun validate() {
        field.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                validateTile(Tile(x, y))
            }
        }
    }

    /**
     * @throws InvalidSudokuException When the tile is invalid.
     */
    private fun validateTile(tile: Tile) {
        field[tile.y].forEachIndexed { x, value ->
            if (value == null) return@forEachIndexed
            if (value == tile.value && x != tile.x)
                throw InvalidSudokuException("The tiles at (${tile.x}, ${tile.y}) and ($x, ${tile.y}) have the same value.)")
        }
        field.forEachIndexed { y, row ->
            if (row[tile.x] == null) return@forEachIndexed
            if (row[tile.x] == tile.value && y != tile.y)
                throw InvalidSudokuException("The tiles at (${tile.x}, ${tile.y}) and (${tile.x}, $y) have the same value.)")
        }

        val squareBegin = Tile((tile.x / squareSize) * squareSize, (tile.y / squareSize) * squareSize)

        val squareEnd = Tile(squareBegin.x + squareSize, squareBegin.y + squareSize)

        (squareBegin..<squareEnd).forEach {
            if (it.value == null) return@forEach
            if (it.value == tile.value && it != tile) {
                throw InvalidSudokuException("The tiles at (${tile.x}, ${tile.y}) and (${it.x}, ${it.y}) have the same value.)")
            }
        }
    }

    val Tile.value: Int?
        get() = this@Sudoku.field[y][x]

    val Tile.inBounds: Boolean
        get() = x in 0..<size && y in 0..<size

    data class Tile(val x: Int, val y: Int) {

        operator fun rangeUntil(tile: Tile): List<Tile> {
            val rangeX = x..<tile.x
            val rangeY = y..<tile.y
            return rangeX.flatMap { cordX ->
                rangeY.map { cordY ->
                    Tile(cordX, cordY)
                }
            }
        }
    }
}