package hf.api.player

import com.jondejong.hf.model.tables.Player.PLAYER
import com.jondejong.hf.model.tables.pojos.Player
import hf.api.api.response.PlayerResponse
import hf.api.app.HFRepository

class PlayerRepository : HFRepository() {
    fun list(): List<PlayerResponse> {
        return context.select(PLAYER.ID, PLAYER.NAME).from(PLAYER).fetchInto(PlayerResponse::class.java)
    }

    fun create(player: Player) {
        context.insertInto(PLAYER, PLAYER.ID, PLAYER.NAME)
            .values(player.id, player.name).execute()
    }

    fun fetch(id: String): List<PlayerResponse> {
        return context.select(PLAYER.ID, PLAYER.NAME)
            .from(PLAYER)
            .where(PLAYER.ID.eq(id))
            .fetchInto(PlayerResponse::class.java)
    }
}