package com.example.minmax

import org.springframework.stereotype.Service

@Service
class TurnService {

    fun calculatePoints(data: NewTurn) = PointsCalculator(data).points
}
