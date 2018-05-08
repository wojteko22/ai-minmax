package com.example.minmax.logic

class PointsCalculator(private val board: Board, private val rowIndex: Int, private val columnIndex: Int) {

    private val row = board[rowIndex]

    private val horizontalPoints: Int by lazy {
        var visited = 0
        for (columnIndex in 0 until row.size) {
            if (!row[columnIndex]) {
                return@lazy 0
            }
            visited++
        }
        return@lazy visited
    }

    private val verticalPoints: Int by lazy {
        var visited = 0
        for (rowIndex in 0 until row.size) {
            if (!board[rowIndex][columnIndex]) {
                return@lazy 0
            }
            visited++
        }
        return@lazy visited
    }

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
        if (visited < 1) {
            return@lazy 0
        }
        return@lazy visited + 1
    }

    private val secondDiagonalPoints by lazy {
        var visited = 0
        var currentRowIndex = rowIndex - 1
        var currentColumnIndex = columnIndex + 1
        val size = board.size
        while (currentRowIndex > -1 && currentColumnIndex < size) {
            if (!board[currentRowIndex][currentColumnIndex]) {
                return@lazy 0
            }
            currentRowIndex--
            currentColumnIndex++
            visited++
        }
        currentRowIndex = rowIndex + 1
        currentColumnIndex = columnIndex - 1
        while (currentRowIndex < size && currentColumnIndex > -1) {
            if (!board[currentRowIndex][currentColumnIndex]) {
                return@lazy 0
            }
            currentRowIndex++
            currentColumnIndex--
            visited++
        }
        if (visited < 1) {
            return@lazy 0
        }
        return@lazy visited + 1
    }

    val points: Int = horizontalPoints + verticalPoints + diagonalPoints + secondDiagonalPoints
}
