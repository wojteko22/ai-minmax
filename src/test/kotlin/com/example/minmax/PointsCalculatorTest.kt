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
        checkPoints(board, 0, 0)
    }

    @Test
    fun `getPoints() calculates vertical points`() {
        val board = listOf(
                listOf(true, false),
                listOf(true, false)
        )
        checkPoints(board, 0, 0)
    }

    @Test
    fun `getPoints() calculates diagonal points`() {
        val board = listOf(
                listOf(true, false),
                listOf(false, true)
        )
        checkPoints(board, 0, 0)
    }

    @Test
    fun `getPoints() calculates points on second diagonal`() {
        val board = listOf(
                listOf(false, true),
                listOf(true, false)
        )
        checkPoints(board, 0, 1)
    }

    private fun checkPoints(board: List<List<Boolean>>, rowIndex: Int, columnIndex: Int) {
        val turn = NewTurn(board, rowIndex, columnIndex)
        val pointsCalculator = PointsCalculator(turn)
        val points = pointsCalculator.points
        assertThat(points).isEqualTo(2)
    }
}
