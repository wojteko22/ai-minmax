package com.example.minmax

class PointsCalculator(private val data: NewTurn) {

    val points: Int
        get() {
            return checkHorizontal() //+ checkVertical() + checkUpperLeftDiagonal() + checkUpperRightDiagonal()
        }

    private fun checkHorizontal(): Int {
        return checkPrevious() + 1 + checkNext()
    }

    private fun checkNext(): Int {
        val row = data.board[data.rowIndex]
        val currentColumn = data.columnIndex
        var visited = 0
        for (column in (currentColumn + 1) until row.size) {
            visited++
            if (!row[column])
                return 0
        }
        return visited
    }

    private fun checkPrevious(): Int {
        val row = data.board[data.rowIndex]
        val currentColumn = data.columnIndex
        var visited = 0
        for (column in (currentColumn - 1) downTo 0) {
            visited++
            if (!row[column])
                return 0
        }
        return visited
    }
}
