package com.jondejong.hf.api.game

import com.jondejong.hf.model.tables.Card.CARD
import com.jondejong.hf.model.tables.CardPosition.CARD_POSITION
import com.jondejong.hf.api.app.HFRepository
import com.jondejong.hf.api.cards.Card
import com.jondejong.hf.api.cards.Number
import com.jondejong.hf.api.cards.Suit

class CardPositionRepository : HFRepository() {

    fun create(cardPosition: CardPosition, shoeId: String) {
        context.insertInto(
            CARD_POSITION,
            CARD_POSITION.ID,
            CARD_POSITION.CARD,
            CARD_POSITION.POSITION,
            CARD_POSITION.SHOE
        )
            .values(cardPosition.id, cardPosition.card.id, cardPosition.position, shoeId)
            .execute()
    }

    fun fetchByShoePosition(shoePosition: ShoePosition): List<CardPosition> {
        return context.select()
            .from(CARD_POSITION)
            .join(CARD)
            .on(CARD_POSITION.CARD.eq(CARD.ID))
            .where(CARD_POSITION.SHOE.eq(shoePosition.shoeId))
            .and(CARD_POSITION.POSITION.eq(shoePosition.position))
            .fetch()
            .map { record ->
                val cardPosition = CardPosition(
                    card = Card(
                        id = record[CARD.ID],
                        suit = Suit.valueOf(record[CARD.SUIT]),
                        number = Number.valueOf(record[CARD.NUMBER]),
                        value = record[CARD.VALUE]
                    ),
                    id = record[CARD_POSITION.ID]
                )
                cardPosition.withPosition(record[CARD_POSITION.POSITION])
                cardPosition
            }


    }

}