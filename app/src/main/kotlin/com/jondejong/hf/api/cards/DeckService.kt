package com.jondejong.hf.api.cards

import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFService

class DeckService : HFService<Card>() {
    override fun setUp(factory: AppFactory) {
    }

    fun getNew(): List<Card> {
        return DeckFactory.deck()
    }
}