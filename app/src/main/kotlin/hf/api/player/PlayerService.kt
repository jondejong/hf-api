package hf.api.player

import com.jondejong.hf.model.tables.pojos.Player
import hf.api.api.response.PlayerResponse
import hf.api.app.AppFactory
import hf.api.app.HFService
import hf.api.security.Password
import java.util.*

class PlayerService : HFService<PlayerResponse>() {
    private lateinit var playerRepository: PlayerRepository
    override fun setUp(factory: AppFactory) {
        this.playerRepository = factory.playerRepository
    }

    fun list(): List<PlayerResponse> {
        return playerRepository.list()
    }

    fun fetch(id: String): PlayerResponse {
        return validateUniqueInstance(playerRepository.fetch(id))
    }

    fun updateToken(id: String, token: String) {
        playerRepository.updateToken(id, token)
    }

    fun fetchByName(name: String): PlayerResponse {
        return validateUniqueInstance(playerRepository.fetchByName(name))
    }

    fun create(name: String, password: String): UUID {
        val id = UUID.randomUUID()
        val salt = UUID.randomUUID().toString()
        val hashedPassword = Password.hash(password, salt)
        playerRepository.create(Player(id.toString(), name, hashedPassword, null, salt))
        return id
    }
}