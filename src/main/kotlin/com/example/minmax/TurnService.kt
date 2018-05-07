package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun newGameState(data: NewTurn): GameState {
        val turnPoints = PointsCalculator(data.board, data.rowIndex, data.columnIndex).points
        val updatedPoints = data.updatedPoints(turnPoints)
        val playerIndex = data.playerIndex
        val nextPlayerIndex = (playerIndex + 1) % 2

        if (data.nextPlayerMode == "consecutive") {
            val board = data.board.map { it.toMutableList() }
            for (rowIndex in 0 until board.size) {
                for (columnIndex in 0 until board.size) {
                    if (!board[rowIndex][columnIndex]) {
                        board[rowIndex][columnIndex] = true
                        val computerPoints = PointsCalculator(board, rowIndex, columnIndex).points
                        val updatedPoints2 = updatedPoints.updated(computerPoints, nextPlayerIndex)
                        return GameState(board, updatedPoints2, playerIndex)
                    }
                }
            }
        }
        return GameState(data.board, updatedPoints, nextPlayerIndex)
    }
}
