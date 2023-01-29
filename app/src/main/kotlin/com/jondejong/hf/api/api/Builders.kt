package com.jondejong.hf.api.api

import com.jondejong.hf.model.tables.pojos.Player
import com.jondejong.hf.api.api.response.PlayerResponse
import java.util.*

val playerResponseFrom = { player: Player ->
    PlayerResponse(id = UUID.fromString(player.id), name = player.name)
}