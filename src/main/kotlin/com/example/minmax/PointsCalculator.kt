package com.example.minmax

class PointsCalculator(data: NewTurn) {

    private val board = data.board
    private val rowIndex = data.rowIndex
    private val columnIndex = data.columnIndex
    private val row = board[rowIndex]

    private val diagonalPoints by lazy {
        var visited = 0
        var currentRowIndex = rowIndex - 1
        var currentColumnIndex = columnIndex - 1
        while (currentRowIndex > -1 && currentColumnIndex > -1) {
            if (!board[currentRowIndex][currentColumnIndex]) {
                return@lazy 0
            }
            currentRowIndex--
            currentColumnIndex--
            visited++
        }
        currentRowIndex = rowIndex + 1
        currentColumnIndex = columnIndex + 1
        val size = board.size
        while (currentRowIndex < size && currentColumnIndex < size) {
            if (!board[currentRowIndex][currentColumnIndex]) {
                return@lazy 0
            }
            currentRowIndex++
            currentColumnIndex++
            visited++

        }
        return@lazy visited + 1
    }

    val points: Int = horizontalPoints + verticalPoints + diagonalPoints

    private val horizontalPoints: Int
        get() {
            var visited = 0
            for (columnIndex in 0 until row.size) {
                if (!row[columnIndex]) {
                    return 0
                }
                visited++
            }
            return visited
        }

    private val verticalPoints: Int
        get() {
            var visited = 0
            for (rowIndex in 0 until row.size) {
                if (!board[rowIndex][columnIndex]) {
                    return 0
                }
                visited++
            }
            return visited
        }
}
