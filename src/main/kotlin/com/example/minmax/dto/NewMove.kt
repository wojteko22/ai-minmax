package com.example.minmax.dto

class NewMove(
        private val gameState: GameState,
        val rowIndex: Int,
        val columnIndex: Int
) {
    val board = gameState.board
    val nextPlayerIndex = gameState.nextPlayerIndex

    fun updatedPoints(pointsGain: Int) = gameState.updatedPoints(pointsGain)
}
