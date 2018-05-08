package com.example.minmax.logic

val pointsAdvantage: StateHeuristics = { gameState, maxPlayerIndex, minPlayerIndex ->
    gameState.points[maxPlayerIndex] - gameState.points[minPlayerIndex]
}
