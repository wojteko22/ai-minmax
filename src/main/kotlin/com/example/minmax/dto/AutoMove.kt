package com.example.minmax.dto

import com.example.minmax.logic.StateHeuristics

class AutoMove(val gameState: GameState, val mode: String, stateHeuristicsName: String, val depth: Int) {

    val stateHeuristics: StateHeuristics = when (stateHeuristicsName) {
        "points-advantage" -> { gameState, maxPlayerIndex, minPlayerIndex: Int ->
            gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
        }
        "points-player-max" -> { gameState, maxPlayerIndex, _: Int ->
            gameState.points[maxPlayerIndex]
        }
        else -> throw NoSuchElementException("No such heuristics")
    }
}
