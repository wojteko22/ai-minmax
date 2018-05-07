package com.example.minmax

class AutoMove(private val gameState: GameState, val mode: String) {
    val board = gameState.board
    val playerIndex = gameState.playerIndex
    val nextPlayerIndex = gameState.nextPlayerIndex

    fun updatedPoints(pointsGain: Int) = gameState.updatedPoints(pointsGain)
}
