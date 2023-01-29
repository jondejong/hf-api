package com.jondejong.hf.api.player

import com.jondejong.hf.api.api.createPlayerLens
import com.jondejong.hf.api.api.idLens
import com.jondejong.hf.api.api.playerLens
import com.jondejong.hf.api.api.playerListLens
import com.jondejong.hf.api.api.response.ID
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFConfigurable
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
        val createPlayerRequest = createPlayerLens(request)
        val id = playerService.create(createPlayerRequest.name, createPlayerRequest.password)
        idLens(ID(id), Response(Status.OK))
    }

    val routes = org.http4k.routing.routes(
        "/" bind Method.GET to listHandler,
        "/" bind Method.POST to createHandler,
        "/{id:.*}" bind Method.GET to fetchHandler,
    )
}