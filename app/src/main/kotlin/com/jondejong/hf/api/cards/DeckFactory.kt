package com.jondejong.hf.api.cards

import java.util.*

object DeckFactory {

    fun deck(): List<Card> {
        val cards = ArrayList<Card>()

        enumValues<Suit>().forEach {
            if (it != Suit.WILD) {
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.ACE, value = 20))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.DUECE, value = 20))
                cards.add(
                    Card(
                        id = UUID.randomUUID().toString(),
                        suit = it,
                        number = Number.THREE,
                        value = if (it == Suit.CLUBS || it == Suit.SPADES) {
                            5
                        } else {
                            300
                        }
                    )
                )
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.FOUR, value = 5))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.FIVE, value = 5))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.SIX, value = 5))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.SEVEN, value = 5))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.EIGHT, value = 10))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.NINE, value = 10))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.TEN, value = 10))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.JACK, value = 10))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.QUEEN, value = 10))
                cards.add(Card(id = UUID.randomUUID().toString(), suit = it, number = Number.KING, value = 10))
            }
        }
        cards.add(Card(id = UUID.randomUUID().toString(), suit = Suit.WILD, number = Number.JOKER, value = 50))
        cards.add(Card(id = UUID.randomUUID().toString(), suit = Suit.WILD, number = Number.JOKER, value = 50))
        return cards
    }
}