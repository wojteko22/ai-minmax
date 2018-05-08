package com.example.minmax

import com.example.minmax.logic.PointsCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PointsCalculatorTest {

    @Test
    fun `getPoints() calculates horizontal points`() {
        val board = listOf(
                listOf(true, true),
                listOf(false, false)
        )
        checkPoints(board, 0, 0, 2)
    }

    @Test
    fun `getPoints() calculates vertical points`() {
        val board = listOf(
                listOf(true, false),
                listOf(true, false)
        )
        checkPoints(board, 0, 0, 2)
    }

    @Test
    fun `getPoints() calculates diagonal points`() {
        val board = listOf(
                listOf(true, false),
                listOf(false, true)
        )
        checkPoints(board, 0, 0, 2)
    }

    @Test
    fun `getPoints() calculates points on second diagonal`() {
        val board = listOf(
                listOf(false, true),
                listOf(true, false)
        )
        checkPoints(board, 0, 1, 2)
    }

    @Test
    fun `getPoints() calculates all point`() {
        val board = listOf(
                listOf(true, true),
                listOf(true, true)
        )
        checkPoints(board, 0, 0, 6)
    }

    private fun checkPoints(board: List<List<Boolean>>, rowIndex: Int, columnIndex: Int, expectedPoints: Int) {
        val pointsCalculator = PointsCalculator(board, rowIndex, columnIndex)
        val points = pointsCalculator.points
        assertThat(points).isEqualTo(expectedPoints)
    }
}
