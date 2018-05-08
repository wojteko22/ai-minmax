package com.example.minmax.logic

import com.example.minmax.dto.GameState

abstract class Algorithm(
        gameState: GameState,
        private val maxDepth: Int,
        private val stateHeuristics: StateHeuristics
) {

    protected val maxPlayerIndex: Int = gameState.playerIndex
    protected val minPlayerIndex = gameState.nextPlayerIndex

    protected fun returnValue(currentDepth: Int, gameState: GameState): Node? {
        if (currentDepth == 0) {
            return null
        }
        val value = stateHeuristics(gameState, maxPlayerIndex, minPlayerIndex)
        return Node(gameState, value)
    }

    protected fun isLastLayer(currentDepth: Int, states: List<GameState>) =
            currentDepth == maxDepth || states.isEmpty()
}
