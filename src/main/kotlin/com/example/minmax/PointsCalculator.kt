package com.example.minmax

class PointsCalculator(data: NewTurn) {

    private val board = data.board
    private val row = board[data.rowIndex]
    private val columnIndex = data.columnIndex

    val points: Int
        get() = horizontalPoints + verticalPoints

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
