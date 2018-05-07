package com.example.minmax

class GameState(val board: Board, val points: Points, val playerIndex: Int) {
    val nextPlayerIndex = (playerIndex + 1) % 2

    fun updatedPoints(turnPoints: Int) = points.updated(turnPoints, playerIndex)
}