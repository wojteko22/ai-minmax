package com.example.minmax.logic

import com.example.minmax.dto.GameState
import com.example.minmax.logic.string.NodeHeuristics

class AlphaBetaPruning(
        gameState: GameState,
        maxDepth: Int,
        stateHeuristics: StateHeuristics,
        nodeHeuristicsName: String
) : Algorithm(gameState, maxDepth, stateHeuristics) {

    private val maxNodeIterator: (list: List<GameState>) -> Iterator<GameState>
    private val minNodeIterator: (list: List<GameState>) -> Iterator<GameState>

    init {
        when (nodeHeuristicsName) {
            NodeHeuristics.Consecutive.value -> {
                val iterator: (list: List<GameState>) -> Iterator<GameState> = { it.iterator() }
                maxNodeIterator = iterator
                minNodeIterator = iterator
            }
            NodeHeuristics.Max.value -> {
                val iterator: (list: List<GameState>) -> Iterator<GameState> = { advantageMaxIterator(it) }
                maxNodeIterator = iterator
                minNodeIterator = iterator
            }
            NodeHeuristics.Min.value -> {
                val iterator: (list: List<GameState>) -> Iterator<GameState> = { advantageMinIterator(it) }
                maxNodeIterator = iterator
                minNodeIterator = iterator
            }
            NodeHeuristics.MaxMin.value -> {
                maxNodeIterator = { advantageMaxIterator(it) }
                minNodeIterator = { advantageMinIterator(it) }
            }
            else -> throw NoSuchElementException("No such node heuristics")
        }
    }

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
        maxNodeIterator(states).forEach {
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
        minNodeIterator(states).forEach {
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

    private fun advantageMaxIterator(list: List<GameState>) = StatesIterator(list) {
        maxBy { pointsAdvantage(it, maxPlayerIndex, minPlayerIndex) }!!
    }

    private fun advantageMinIterator(list: List<GameState>) = StatesIterator(list) {
        minBy { pointsAdvantage(it, maxPlayerIndex, minPlayerIndex) }!!
    }
}
