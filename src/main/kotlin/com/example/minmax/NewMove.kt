package com.example.minmax

class NewMove(
        gameState: GameState,
        val rowIndex: Int,
        val columnIndex: Int
) {
    val board = gameState.board
    private val points = gameState.points
    private val playerIndex = gameState.playerIndex
    val nextPlayerIndex = gameState.nextPlayerIndex

    fun updatedPoints(turnPoints: Int) = points.updated(turnPoints, playerIndex)
}
