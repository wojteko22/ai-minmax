package com.example.minmax

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TurnController(private val service: TurnService) {

    @PostMapping
    fun playNextTurn(@RequestBody data: NewTurn) = service.newGameState(data)
}
