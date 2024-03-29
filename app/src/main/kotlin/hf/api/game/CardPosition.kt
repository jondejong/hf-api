package hf.api.game

import hf.api.cards.Card

class CardPosition(val id: String, val card: Card) {
    var position: Int = -1

    fun withPosition(position: Int): CardPosition {
        this.position = position
        return this
    }
}