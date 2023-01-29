package com.jondejong.hf.api.cards

import com.jondejong.hf.api.api.cardListLens
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFConfigurable
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind

class DeckRoutes : HFConfigurable {
    private lateinit var deckService: DeckService
    override fun setUp(factory: AppFactory) {
        this.deckService = factory.deckService
    }

    private val deckHandler = { request: Request ->
        cardListLens(deckService.getNew(), Response(Status.OK))
    }

    val routes = org.http4k.routing.routes(
        "/" bind Method.GET to deckHandler
    )
}