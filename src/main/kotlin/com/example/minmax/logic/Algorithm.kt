package com.example.minmax.logic

import com.example.minmax.dto.GameState

abstract class Algorithm(gameState: GameState) {

    protected val maxPlayerIndex: Int = gameState.playerIndex

    protected fun returnValue(currentDepth: Int, gameState: GameState): Node? {
        if (currentDepth == 0) {
            return null
        }
        val minPlayerIndex = (maxPlayerIndex + 1) % 2
        val value = gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
        return Node(gameState, value)
    }

    protected fun isLastLayer(currentDepth: Int, states: List<GameState>) =
            currentDepth == 4 || states.isEmpty()
}