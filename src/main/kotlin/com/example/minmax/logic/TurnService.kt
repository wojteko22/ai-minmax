package com.example.minmax.logic

import com.example.minmax.dto.AutoMove
import com.example.minmax.dto.GameState
import com.example.minmax.dto.NewMove
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

@Service
class TurnService {

    fun makeMove(data: NewMove): GameState {
        val turnPoints = PointsCalculator(data.board, data.rowIndex, data.columnIndex).points
        val updatedPoints = data.updatedPoints(turnPoints)
        return GameState(data.board, updatedPoints, data.nextPlayerIndex)
    }

    fun makeAutoMove(autoMove: AutoMove): GameState? {
        var newState: GameState? = null
        val timeInMillis = measureTimeMillis {
            newState = autoMove.makeMove()
        }
        println("$autoMove: $timeInMillis")
        return newState
    }
}
