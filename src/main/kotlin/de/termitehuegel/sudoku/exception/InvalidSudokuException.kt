package de.termitehuegel.sudoku.exception

class InvalidSudokuException : Exception {

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(message: String?) : super(message)

    constructor() : super()
}