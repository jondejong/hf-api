package hf.api.cards

import hf.api.app.AppFactory
import hf.api.app.HFService

class DeckService : HFService<Card>() {
    override fun setUp(factory: AppFactory) {
    }

    fun getNew(): List<Card> {
        return DeckFactory.deck()
    }
}