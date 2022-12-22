package hf.api.player

import hf.api.api.playerResponseFrom
import hf.api.api.response.LoginResponse
import hf.api.app.AppFactory
import hf.api.app.HFService
import hf.api.exception.ItemNotFoundException
import hf.api.exception.UnauthenticatedException
import hf.api.security.Password
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