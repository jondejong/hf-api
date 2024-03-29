package hf.api.game

import hf.api.api.cardPositionLens
import hf.api.api.createGameLens
import hf.api.api.idLens
import hf.api.api.response.ID
import hf.api.app.AppFactory
import hf.api.app.HFConfigurable
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import java.util.*

class GameRoutes : HFConfigurable {

    lateinit var gameService: GameService

    override fun setUp(factory: AppFactory) {
        this.gameService = factory.gameService
    }

    private val createHandler = { request: Request ->
        val createGameRequest = createGameLens(request)
        val id = gameService.create(createGameRequest.shoeSize)
        idLens(ID(UUID.fromString(id)), Response(Status.OK))
    }

    private val nextCardHandler = { request: Request ->
        cardPositionLens(
            gameService.nextCard(request.path("gameId") ?: ""),
            Response(Status.OK)
        )
    }

    val routes = org.http4k.routing.routes(
        "/" bind Method.POST to createHandler,
        "/{gameId:.*}/next" bind Method.GET to nextCardHandler
    )
}