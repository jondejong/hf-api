package hf.api.player

import com.jondejong.hf.model.tables.pojos.Player
import hf.api.api.response.PlayerResponse
import hf.api.app.AppFactory
import hf.api.app.HFService
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

    fun create(name: String): UUID {
        val id = UUID.randomUUID()
        playerRepository.create(Player(id.toString(), name))
        return id
    }
}