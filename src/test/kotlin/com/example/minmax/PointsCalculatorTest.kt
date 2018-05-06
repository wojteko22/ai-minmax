package com.example.minmax

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PointsCalculatorTest {

    @Test
    fun getPoints() {
        val board = listOf(
                listOf(true, true),
                listOf(false, false)
        )
        val turn = NewTurn(board, 0, 0)
        val pointsCalculator = PointsCalculator(turn)
        val points = pointsCalculator.points
        assertThat(points).isEqualTo(2)
    }

    @Test
    fun getPoints2() {
        val board = listOf(
                listOf(true, false),
                listOf(true, false)
        )
        val turn = NewTurn(board, 0, 0)
        val pointsCalculator = PointsCalculator(turn)
        val points = pointsCalculator.points
        assertThat(points).isEqualTo(2)
    }
}
