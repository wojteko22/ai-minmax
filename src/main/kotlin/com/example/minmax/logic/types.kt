package com.example.minmax.logic

import com.example.minmax.dto.GameState

typealias Board = List<List<Boolean>>
typealias Points = List<Int>

fun Points.updated(turnPoints: Int, index: Int): Points = toMutableList().apply { this[index] += turnPoints }

typealias StateHeuristics = (gameState: GameState, maxPlayerIndex: Int, minPlayerIndex: Int) -> Int
