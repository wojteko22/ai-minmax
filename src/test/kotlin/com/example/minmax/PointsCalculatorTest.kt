package com.example.minmax

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PointsCalculatorTest {

    @Test
    fun `getPoints() calculates horizontal points`() {
        val board = listOf(
                listOf(true, true),
                listOf(false, false)
        )
        checkPoints(board)
    }

    @Test
    fun `getPoints() calculates vertical points`() {
        val board = listOf(
                listOf(true, false),
                listOf(true, false)
        )
        checkPoints(board)
    }

    private fun checkPoints(board: List<List<Boolean>>) {
        val turn = NewTurn(board, 0, 0)
        val pointsCalculator = PointsCalculator(turn)
        val points = pointsCalculator.points
        assertThat(points).isEqualTo(2)
    }


}
