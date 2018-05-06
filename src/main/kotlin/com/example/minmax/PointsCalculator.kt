package com.example.minmax

class PointsCalculator(data: NewTurn) {

    private val row = data.board[data.rowIndex]

    val points: Int
        get() = horizontalPoints //+ checkVertical() + checkUpperLeftDiagonal() + checkUpperRightDiagonal()


    private val horizontalPoints: Int
        get() {
            var visited = 0
            for (column in 0 until row.size) {
                if (!row[column]) {
                    return 0
                }
                visited++
            }
            return visited
        }
}
