package com.example.minmax.analyser

import com.example.minmax.dto.AutoMove
import com.example.minmax.dto.GameState
import com.example.minmax.logic.Board
import com.example.minmax.logic.string.Mode
import com.example.minmax.logic.string.NodeHeuristics
import com.example.minmax.logic.string.StateHeuristicsEnum
import kotlin.math.roundToInt
import kotlin.system.measureTimeMillis

const val size = 7
val mode = Mode.AlphaBeta
val stateHeuristics = StateHeuristicsEnum.PointsAdvantage
val nodeHeuristics = NodeHeuristics.Consecutive
const val depth = 3

val board: Board = List(size) { List(size) { false } }
val points = listOf(0, 0)
val state = GameState(board, points, 0)
val moveData = AutoMove(state, mode.value, stateHeuristics.value, nodeHeuristics.value, depth)

fun main(args: Array<String>) {
    measureAverageTime {
        playGame()
    }
}

private fun playGame() {
    var newState = moveData.makeMove()
    while (newState != null) {
        val newMoveData = moveData.copy(gameState = newState)
        newState = newMoveData.makeMove()
    }
}

private fun makeMove() {
    moveData.makeMove()
}

private fun measureAverageTime(measured: () -> Any?) {
    val times = 5
    val average = (1..times)
            .map {
                measureTime(it) { measured() }
            }
            .average()
            .roundToInt()
    println("average: $average")
}

private fun measureTime(number: Int = 1, measured: () -> Any?): Long {
    val time = measureTimeMillis { measured() }
    println("$number. $time")
    return time
}
