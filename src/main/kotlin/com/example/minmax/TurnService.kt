package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun newGameState(data: NewTurn): GameState {
        val turnPoints = PointsCalculator(data).points
        val updatedPoints = data.points.toMutableList().apply {
            this[data.playerIndex] += turnPoints
        }
        return GameState(data.board, updatedPoints)
    }
}
