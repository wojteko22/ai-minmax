package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun makeMove(data: NewMove): GameState {
        val turnPoints = PointsCalculator(data.board, data.rowIndex, data.columnIndex).points
        val updatedPoints = data.updatedPoints(turnPoints)
        return GameState(data.board, updatedPoints, data.nextPlayerIndex)
    }

    fun makeAutoMove(data: AutoMove): GameState? {
        val states = allAvailableStates(data)
        return when (data.mode) {
            "consecutive" -> states.firstOrNull()
            "points" -> states.maxBy { it.points[data.playerIndex] }
            else -> null
        }
    }

    private fun allAvailableStates(data: AutoMove): List<GameState> {
        val board = data.board
        val states = mutableListOf<GameState>()
        for (rowIndex in 0 until board.size) {
            for (columnIndex in 0 until board.size) {
                if (!board[rowIndex][columnIndex]) {
                    val currentBoard = board.map { it.toMutableList() }
                    currentBoard[rowIndex][columnIndex] = true
                    val pointsGain = PointsCalculator(currentBoard, rowIndex, columnIndex).points
                    val nextPlayerIndex = data.nextPlayerIndex
                    val updatedPoints = data.updatedPoints(pointsGain)
                    val gameState = GameState(currentBoard, updatedPoints, nextPlayerIndex)
                    states += gameState
                }
            }
        }
        return states
    }
}
