package com.example.minmax

class NewTurn(
        gameState: GameState,
        val playerIndex: Int,
        val rowIndex: Int,
        val columnIndex: Int
) {
    val board = gameState.board
    val points = gameState.points
}
