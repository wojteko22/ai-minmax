package com.example.minmax

typealias Board = List<List<Boolean>>
typealias Points = List<Int>

fun Points.updated(turnPoints: Int, index: Int): Points = toMutableList().apply { this[index] += turnPoints }