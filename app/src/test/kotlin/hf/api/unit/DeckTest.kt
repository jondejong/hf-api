package hf.api.unit

import hf.api.cards.DeckFactory
import hf.api.cards.Number
import hf.api.cards.Suit
import org.junit.Test
import kotlin.test.assertEquals

class DeckTest {
    @Test
    fun `can create a deck`() {
        var actualNumberOfJokers = 0
        val deck = DeckFactory.deck()
        assertEquals(54, deck.size)
        val expectedMap = mutableMapOf<Suit, MutableMap<Number, Boolean>>()
        Suit.values().forEach {suit ->
            expectedMap[suit] = mutableMapOf()
            if(suit != Suit.WILD) {
                Number.values().forEach {number ->
                    if(number != Number.JOKER) {
                        expectedMap[suit]!![number] = false
                    }
                }
            }
        }

        deck.forEach{card->
            if(card.suit == Suit.WILD && card.number == Number.JOKER) {
                actualNumberOfJokers++
            }
            expectedMap[card.suit]!![card.number] = true
        }

        assertEquals(2, actualNumberOfJokers)
        expectedMap.keys.forEach{suit ->
            expectedMap[suit]!!.keys.forEach{number ->
                assertEquals(true, expectedMap[suit]!![number])
            }
        }
    }
}