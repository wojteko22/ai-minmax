package com.example.minmax

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TurnController(private val service: TurnService) {

    @PostMapping
    fun makeMove(@RequestBody data: NewMove) = service.makeMove(data)

    @PostMapping("/auto")
    fun makeAutoMove(@RequestBody data: AutoMove) = service.makeAutoMove(data)
}
