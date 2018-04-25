package com.example.minmax

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TurnController {

    @PostMapping
    fun playNextTurn(@RequestBody data: NewTurn): Int {
        println(data.board)
        return 2
    }
}
