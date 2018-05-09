package com.example.minmax.dto

import com.example.minmax.logic.*
import com.example.minmax.logic.string.Mode
import com.example.minmax.logic.string.StateHeuristicsEnum

data class AutoMove(
        private val gameState: GameState,
        private val mode: String,
        private val stateHeuristicsName: String,
        private val nodeHeuristicsName: String,
        private val depth: Int
) {

    private val stateHeuristics: StateHeuristics = when (stateHeuristicsName) {
        StateHeuristicsEnum.PointsAdvantage.value -> pointsAdvantage
        StateHeuristicsEnum.PointsPlayerMax.value -> { gameState, maxPlayerIndex, _ ->
            gameState.points[maxPlayerIndex]
        }
        StateHeuristicsEnum.PointsOpponentMin.value -> { gameState, _, minPlayerIndex ->
            -gameState.points[minPlayerIndex]
        }
        else -> throw NoSuchElementException("No such heuristics")
    }

    fun makeMove(): GameState? = when (mode) {
        Mode.MinMax.value -> MinMax(gameState, depth, stateHeuristics).bestState
        Mode.AlphaBeta.value -> AlphaBetaPruning(gameState, depth, stateHeuristics, nodeHeuristicsName).bestState
        else -> null
    }

    override fun toString(): String = "$mode $nodeHeuristicsName:"
}
