package com.example.minmax.dto

import com.example.minmax.logic.AlphaBetaPruning
import com.example.minmax.logic.MinMax
import com.example.minmax.logic.StateHeuristics
import com.example.minmax.logic.pointsAdvantage

class AutoMove(
        private val gameState: GameState,
        private val mode: String,
        stateHeuristicsName: String,
        private val nodeHeuristicsName: String,
        private val depth: Int
) {

    private val stateHeuristics: StateHeuristics = when (stateHeuristicsName) {
        "points-advantage" -> pointsAdvantage
        "points-player-max" -> { gameState, maxPlayerIndex, _ ->
            gameState.points[maxPlayerIndex]
        }
        "points-opponent-min" -> { gameState, _, minPlayerIndex ->
            -gameState.points[minPlayerIndex]
        }
        else -> throw NoSuchElementException("No such heuristics")
    }

    fun makeMove(): GameState? = when (mode) {
        "min-max" -> MinMax(gameState, depth, stateHeuristics).bestState
        "alpha-beta" -> AlphaBetaPruning(gameState, depth, stateHeuristics, nodeHeuristicsName).bestState
        else -> null
    }

    override fun toString(): String = "$mode $nodeHeuristicsName:"
}
