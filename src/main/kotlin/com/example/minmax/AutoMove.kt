package com.example.minmax

class AutoMove(gameState: GameState, val mode: String) {
    val board = gameState.board
    val points = gameState.points
    val playerIndex = gameState.playerIndex
    val nextPlayerIndex = gameState.nextPlayerIndex
}
