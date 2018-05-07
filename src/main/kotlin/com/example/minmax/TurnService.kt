package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun makeMove(data: NewMove): GameState {
        val turnPoints = PointsCalculator(data.board, data.rowIndex, data.columnIndex).points
        val updatedPoints = data.updatedPoints(turnPoints)
        return GameState(data.board, updatedPoints, data.nextPlayerIndex)
    }

    fun makeAutoMove(data: AutoMove): GameState {
        println(data.mode)
        val board = data.board.map { it.toMutableList() }
        for (rowIndex in 0 until board.size) {
            for (columnIndex in 0 until board.size) {
                if (!board[rowIndex][columnIndex]) {
                    board[rowIndex][columnIndex] = true
                    val computerPoints = PointsCalculator(board, rowIndex, columnIndex).points
                    val updatedPoints2 = data.points.updated(computerPoints, data.nextPlayerIndex)
                    return GameState(board, updatedPoints2, data.nextPlayerIndex)
                }
            }
        }
        throw RuntimeException("The game ended")
    }
}
