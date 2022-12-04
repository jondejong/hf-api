package hf.api.app

import hf.api.cards.DeckRoutes
import hf.api.cards.DeckService
import hf.api.properties.HFApiProperties
import org.http4k.core.RequestContexts

class AppFactory(properties: HFApiProperties) {
    companion object {
        const val AUTHENTICATION_HEADER = "X-AUTH-TOKEN"
        const val AUTHENTICATED_USER = "AUTHENTICATED-USER"
        const val IS_ADMIN = "IS-ADMIN"
    }

    // HandleRequests
    val contexts = RequestContexts()

    // Server Port
    val port = properties.serverProperties.port

    // Deck
    val deckService = DeckService()
    val deckRoutes = DeckRoutes()

    init {
        // Deck
        deckService.setUp(this)
        deckRoutes.setUp(this)
    }
}