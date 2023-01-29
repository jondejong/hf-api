package com.jondejong.hf.api.cards

import com.jondejong.hf.model.tables.Card.CARD
import com.jondejong.hf.api.app.HFRepository

class CardRepository : HFRepository() {

    fun create(card: Card) {
        context.insertInto(CARD, CARD.ID, CARD.SUIT, CARD.NUMBER, CARD.VALUE)
            .values(card.id, card.suit.toString(), card.number.toString(), card.value)
            .execute()
    }
}