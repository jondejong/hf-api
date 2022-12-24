package hf.api.game

import com.jondejong.hf.model.tables.CardPosition.CARD_POSITION
import hf.api.app.HFRepository

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
}