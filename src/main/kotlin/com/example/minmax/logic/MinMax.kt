package com.example.minmax.logic

import com.example.minmax.dto.GameState

class MinMax(gameState: GameState) {

    private val maxPlayerIndex: Int = gameState.playerIndex

    val bestState= minMax(gameState, 0)?.state

    private fun minMax(gameState: GameState, currentDepth: Int): Node? {
        val states = gameState.allAvailableStates()
        if (currentDepth == 4 || states.isEmpty()) {
            if (currentDepth == 0) {
                return null
            }
            val minPlayerIndex = (maxPlayerIndex + 1) % 2
            val value = gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
            return Node(gameState, value)
        }
        return if (gameState.playerIndex == maxPlayerIndex) {
            node(states, currentDepth, gameState, ::max)
        } else {
            node(states, currentDepth, gameState, ::min)
        }
    }

    private fun node(
            states: List<GameState>,
            currentDepth: Int,
            gameState: GameState,
            bestChildSelector: (List<Node>) -> Node
    ): Node {
        val estimatedChildren = states.map { minMax(it, currentDepth + 1)!! }
        val bestChild = bestChildSelector(estimatedChildren)
        return if (currentDepth == 0) {
            bestChild
        } else {
            Node(gameState, bestChild.value)
        }
    }

    private fun max(nodes: List<Node>): Node = nodes.maxBy { it.value }!!

    private fun min(nodes: List<Node>): Node = nodes.minBy { it.value }!!
}