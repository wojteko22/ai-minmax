package com.example.minmax.logic

import com.example.minmax.dto.GameState

class AlphaBetaPruning(gameState: GameState, maxDepth: Int): Algorithm(gameState, maxDepth) {

    val bestState = search(gameState, 0, Node(null, Int.MIN_VALUE), Node(null, Int.MAX_VALUE))?.state

    private fun search(gameState: GameState, currentDepth: Int, alpha: Node, beta: Node): Node? {
        val states = gameState.allAvailableStates()
        return when {
            isLastLayer(currentDepth, states) -> returnValue(currentDepth, gameState)
            gameState.playerIndex == maxPlayerIndex -> maxNode(states, currentDepth, gameState, alpha, beta)
            else -> minNode(states, currentDepth, gameState, alpha, beta)
        }
    }

    private fun maxNode(
            states: List<GameState>,
            currentDepth: Int,
            gameState: GameState,
            alpha: Node,
            beta: Node
    ): Node {
        var currentAlpha = alpha
        states.forEach {
            val score = search(it, currentDepth + 1, currentAlpha, beta)!!
            if (score.value > currentAlpha.value) {
                currentAlpha = score
            }
            if (currentAlpha.value >= beta.value) {
                return Node(gameState, currentAlpha.value)
            }
        }
        return if (currentDepth == 0) {
            currentAlpha
        } else {
            Node(gameState, currentAlpha.value)
        }
    }

    private fun minNode(
            states: List<GameState>,
            currentDepth: Int,
            gameState: GameState,
            alpha: Node,
            beta: Node
    ): Node {
        var currentBeta = beta
        states.forEach {
            val score = search(it, currentDepth + 1, alpha, currentBeta)!!
            if (score.value < currentBeta.value) {
                currentBeta = score
            }
            if (alpha.value >= beta.value) {
                return Node(gameState, currentBeta.value)
            }
        }
        return Node(gameState, currentBeta.value)
    }
}