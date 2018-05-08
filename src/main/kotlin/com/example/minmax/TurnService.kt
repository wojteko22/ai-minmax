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
        return when (data.mode) {
            "consecutive" -> {
                val states = data.gameState.allAvailableStates()
                states.firstOrNull()
            }
            "points" -> minMax(data.gameState, data.playerIndex, 0)?.state
            else -> null
        }
    }

    fun minMax(gameState: GameState, maxPlayerIndex: Int, depth: Int): Node? {
        val states = gameState.allAvailableStates()
        if (depth == 2 || states.isEmpty()) {
            val minPlayerIndex = (maxPlayerIndex + 1) % 2
            val value = gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
            return Node(gameState, value)
        }
        return if (gameState.playerIndex == maxPlayerIndex) {
            val bestChild = states.map { minMax(it, maxPlayerIndex, depth + 1)!! }.maxBy { it.value }!!
            if (depth == 0) {
                bestChild
            } else {
                Node(gameState, bestChild.value)
            }
        } else {
            val bestChild = states.map { minMax(it, maxPlayerIndex, depth + 1)!! }.minBy { it.value }!!
            if (depth == 0) {
                bestChild
            } else {
                Node(gameState, bestChild.value)
            }
        }
    }

    class Node(val state: GameState, val value: Int)
}
