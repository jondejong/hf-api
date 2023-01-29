package com.jondejong.hf.api.app

import AuthenticationFilter
import com.zaxxer.hikari.HikariDataSource
import com.jondejong.hf.api.cards.CardRepository
import com.jondejong.hf.api.cards.DeckRoutes
import com.jondejong.hf.api.cards.DeckService
import com.jondejong.hf.api.database.DatabaseConfig
import com.jondejong.hf.api.game.*
import com.jondejong.hf.api.player.*
import com.jondejong.hf.api.properties.HFApiProperties
import org.http4k.core.RequestContexts

class AppFactory(properties: HFApiProperties) {
    companion object {
        const val AUTHENTICATION_HEADER = "X-AUTH-TOKEN"
        const val AUTHENTICATED_USER = "AUTHENTICATED-USER"
        const val IS_ADMIN = "IS-ADMIN"
    }

    private val databaseConfig = DatabaseConfig.build(properties.databaseProperties)
    val dataSource = HikariDataSource(databaseConfig)

    // Filter
    val authenticationFilter = AuthenticationFilter()

    // HandleRequests
    val contexts = RequestContexts()

    // Server Port
    val port = properties.serverProperties.port

    // Deck
    val deckService = DeckService()
    val deckRoutes = DeckRoutes()

    // Player
    val playerRepository = PlayerRepository()
    val playerService = PlayerService()
    val hydratedPlayerService = HydratedPlayerService()
    val playerRoutes = PlayerRoutes()
    val loginService = LoginService()
    val loginRoutes = LoginRoutes()

    // Game
    val cardRepository = CardRepository()
    val cardPositionRepository = CardPositionRepository()
    val shoeRepository = ShoeRepository()
    val shoeService = ShoeService()
    val gameRepository = GameRepository()
    val gameService = GameService()
    val gameRoutes = GameRoutes()

    init {
        // Filer
        authenticationFilter.setUp(this)

        // Deck
        deckService.setUp(this)
        deckRoutes.setUp(this)

        // Player
        playerRepository.setUp(this)
        playerService.setUp(this)
        playerRoutes.setUp(this)
        loginService.setUp(this)
        hydratedPlayerService.setUp(this)
        loginRoutes.setUp(this)

        // Game
        cardRepository.setUp(this)
        cardPositionRepository.setUp(this)
        shoeRepository.setUp(this)
        shoeService.setUp(this)
        gameRepository.setUp(this)
        gameService.setUp(this)
        gameRoutes.setUp(this)
    }
}