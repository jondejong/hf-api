package com.jondejong.hf.api.player

import com.jondejong.hf.api.api.playerResponseFrom
import com.jondejong.hf.api.api.response.LoginResponse
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFService
import com.jondejong.hf.api.exception.ItemNotFoundException
import com.jondejong.hf.api.exception.UnauthenticatedException
import com.jondejong.hf.api.security.Password
import java.util.*

class LoginService : HFService<LoginResponse>() {

    private lateinit var playerService: PlayerService
    private lateinit var hydratedPlayerService: HydratedPlayerService

    override fun setUp(factory: AppFactory) {
        this.playerService = factory.playerService
        this.hydratedPlayerService = factory.hydratedPlayerService
    }

    fun validateUser(name: String, password: String): LoginResponse {
        try {
            val player = hydratedPlayerService.fetchByName(name)
            Password.validate(player.password, password, player.salt)
            val token = UUID.randomUUID().toString()
            playerService.updateToken(
                id = player.id,
                token = token
            )
            return LoginResponse(
                playerResponseFrom(player),
                token
            )
        } catch (itemNotFoundException: ItemNotFoundException) {
            throw UnauthenticatedException()
        }
    }
}