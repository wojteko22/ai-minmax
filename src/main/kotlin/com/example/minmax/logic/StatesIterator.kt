package com.example.minmax.logic

import com.example.minmax.dto.GameState

class StatesIterator(list: List<GameState>, private val selector: List<GameState>.() -> GameState) : Iterator<GameState> {

    private val items = list.toMutableList()

    override fun hasNext(): Boolean = items.isNotEmpty()

    override fun next(): GameState {
        val element = items.selector()
        items.remove(element)
        return element
    }
}
