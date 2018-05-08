package com.example.minmax.logic

import com.example.minmax.dto.GameState

class MinMax(gameState: GameState, maxDepth: Int, stateHeuristics: StateHeuristics) :
        Algorithm(gameState, maxDepth, stateHeuristics) {

    val bestState = minMax(gameState, 0)?.state

    private fun minMax(gameState: GameState, currentDepth: Int): Node? {
        val states = gameState.allAvailableStates()
        return when {
            isLastLayer(currentDepth, states) -> returnValue(currentDepth, gameState)
            gameState.playerIndex == maxPlayerIndex -> node(states, currentDepth, gameState, ::max)
            else -> node(states, currentDepth, gameState, ::min)
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