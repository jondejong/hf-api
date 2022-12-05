package hf.api.player

import hf.api.api.createPlayerLens
import hf.api.api.idLens
import hf.api.api.playerLens
import hf.api.api.playerListLens
import hf.api.api.response.ID
import hf.api.app.AppFactory
import hf.api.app.HFConfigurable
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path

class PlayerRoutes : HFConfigurable {
    private lateinit var playerService: PlayerService
    override fun setUp(factory: AppFactory) {
        this.playerService = factory.playerService
    }

    private val listHandler = { request: Request ->
        playerListLens(playerService.list(), Response(Status.OK))
    }

    private val fetchHandler = { request: Request ->
        val id = request.path("id") ?: ""
        playerLens(playerService.fetch(id), Response(Status.OK))
    }

    private val createHandler = { request: Request ->
        val name = createPlayerLens(request).name
        val id = playerService.create(name)
        idLens(ID(id), Response(Status.OK))
    }

    val routes = org.http4k.routing.routes(
        "/" bind Method.GET to listHandler,
        "/" bind Method.POST to createHandler,
        "/{id:.*}" bind Method.GET to fetchHandler,
    )
}