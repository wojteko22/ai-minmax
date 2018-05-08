package com.example.minmax.dto

import com.example.minmax.logic.AlphaBetaPruning
import com.example.minmax.logic.MinMax
import com.example.minmax.logic.StateHeuristics

class AutoMove(
        private val gameState: GameState,
        private val mode: String,
        stateHeuristicsName: String,
        private val depth: Int
) {

    private val stateHeuristics: StateHeuristics = when (stateHeuristicsName) {
        "points-advantage" -> { gameState, maxPlayerIndex, minPlayerIndex ->
            gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
        }
        "points-player-max" -> { gameState, maxPlayerIndex, _ ->
            gameState.points[maxPlayerIndex]
        }
        "points-opponent-min" -> { gameState, _, minPlayerIndex ->
            -gameState.points[minPlayerIndex]
        }
        else -> throw NoSuchElementException("No such heuristics")
    }

    fun makeMove(): GameState? = when (mode) {
        "consecutive" -> gameState.allAvailableStates().firstOrNull()
        "points" -> MinMax(gameState, depth, stateHeuristics).bestState
        "alpha-beta" -> AlphaBetaPruning(gameState, depth, stateHeuristics).bestState
        else -> null
    }
}
