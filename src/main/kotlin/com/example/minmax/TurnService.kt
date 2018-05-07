package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun newGameState(data: NewTurn): GameState {
        val points = PointsCalculator(data).points
        return GameState(data.board, points)
    }
}
