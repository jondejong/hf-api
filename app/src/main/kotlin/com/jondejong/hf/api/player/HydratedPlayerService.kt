package com.jondejong.hf.api.player

import com.jondejong.hf.model.tables.pojos.Player
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFService

class HydratedPlayerService : HFService<Player>() {
    lateinit var playerRepository: PlayerRepository
    override fun setUp(factory: AppFactory) {
        this.playerRepository = factory.playerRepository
    }

    fun fetch(id: String): Player {
        return validateUniqueInstance(playerRepository.fetchHydrated(id))
    }

    fun fetchByName(name: String): Player {
        return validateUniqueInstance(playerRepository.fetchHydratedByName(name))
    }

    fun fetchByToken(token: String): Player {
        return validateUniqueInstance(playerRepository.fetchHydratedByToken(token))
    }
}