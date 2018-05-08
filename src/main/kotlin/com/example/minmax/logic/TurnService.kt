package com.example.minmax.logic

import com.example.minmax.dto.AutoMove
import com.example.minmax.dto.GameState
import com.example.minmax.dto.NewMove
import org.springframework.stereotype.Service

@Service
class TurnService {

    fun makeMove(data: NewMove): GameState {
        val turnPoints = PointsCalculator(data.board, data.rowIndex, data.columnIndex).points
        val updatedPoints = data.updatedPoints(turnPoints)
        return GameState(data.board, updatedPoints, data.nextPlayerIndex)
    }

    fun makeAutoMove(data: AutoMove): GameState? = when (data.mode) {
        "consecutive" -> data.gameState.allAvailableStates().firstOrNull()
        "points" -> MinMax(data.gameState, data.depth, data.stateHeuristics).bestState
        "alpha-beta" -> AlphaBetaPruning(data.gameState, data.depth, data.stateHeuristics).bestState
        else -> null
    }
}
