package com.example.minmax.logic

typealias Board = List<List<Boolean>>
typealias Points = List<Int>

fun Points.updated(turnPoints: Int, index: Int): Points = toMutableList().apply { this[index] += turnPoints }