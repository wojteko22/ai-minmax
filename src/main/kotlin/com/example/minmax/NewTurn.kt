package com.example.minmax

class NewTurn(
        gameState: GameState,
        val rowIndex: Int,
        val columnIndex: Int
) {
    val board = gameState.board
}
