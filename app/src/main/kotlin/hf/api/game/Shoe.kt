package hf.api.game

import hf.api.cards.Card
import hf.api.cards.DeckFactory
import java.util.*
import kotlin.random.Random

class Shoe(val id: String, size: Int) {

    companion object {
        const val SWAP_PER_DECK = 1000000
    }

    var cards: MutableList<CardPosition> = mutableListOf()
    var nextPosition = 0

    init {
        val shuffledCards: MutableMap<Int, CardPosition> = HashMap()
        for (deckNumber in 1..size) {
            DeckFactory.deck().forEach { card: Card ->
                shuffledCards[shuffledCards.size] =
                    CardPosition(UUID.randomUUID().toString(), card).withPosition(shuffledCards.size)
            }
        }

        for (i in 0..SWAP_PER_DECK * size) {
            val position1 = Random.nextInt(0, shuffledCards.size)
            val position2 = Random.nextInt(0, shuffledCards.size)

            val card1 = shuffledCards[position1]!!
            val card2 = shuffledCards[position2]!!

            shuffledCards[position1] = card2.withPosition(position1)
            shuffledCards[position2] = card1.withPosition(position2)
        }

        cards.addAll(shuffledCards.values.sortedBy { it.position })
    }
}