package com.example.minmax

import org.assertj.core.api.Assertions.assertThat

internal class PointsCalculatorTest {

    @org.junit.jupiter.api.Test
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
}
