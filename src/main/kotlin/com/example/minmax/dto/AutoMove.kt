package com.example.minmax.dto

class AutoMove(val gameState: GameState, val mode: String) {
    val playerIndex = gameState.playerIndex
}
