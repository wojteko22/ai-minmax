package com.example.minmax

class NewTurn(
        gameState: GameState,
        val rowIndex: Int,
        val columnIndex: Int,
        val nextPlayerMode: String
) {
    val board = gameState.board
    private val points = gameState.points
    val playerIndex = gameState.playerIndex

    fun updatedPoints(turnPoints: Int) = points.updated(turnPoints, playerIndex)
}
