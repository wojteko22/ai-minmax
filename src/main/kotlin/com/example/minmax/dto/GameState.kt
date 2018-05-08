package com.example.minmax.dto

import com.example.minmax.logic.Board
import com.example.minmax.logic.Points
import com.example.minmax.logic.PointsCalculator
import com.example.minmax.logic.updated

class GameState(val board: Board, val points: Points, val playerIndex: Int) {

    val nextPlayerIndex = (playerIndex + 1) % 2

    fun updatedPoints(turnPoints: Int) = points.updated(turnPoints, playerIndex)

    fun allAvailableStates(): List<GameState> {
        val states = mutableListOf<GameState>()
        for (rowIndex in 0 until board.size) {
            for (columnIndex in 0 until board.size) {
                if (!board[rowIndex][columnIndex]) {
                    val currentBoard = board.map { it.toMutableList() }
                    currentBoard[rowIndex][columnIndex] = true
                    val pointsGain = PointsCalculator(currentBoard, rowIndex, columnIndex).points
                    val updatedPoints = updatedPoints(pointsGain)
                    val gameState = GameState(currentBoard, updatedPoints, nextPlayerIndex)
                    states += gameState
                }
            }
        }
        return states
    }
}