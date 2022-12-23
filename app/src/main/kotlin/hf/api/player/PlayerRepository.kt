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
        context.insertInto(PLAYER, PLAYER.ID, PLAYER.NAME, PLAYER.SALT, PLAYER.PASSWORD)
            .values(player.id, player.name, player.salt, player.password).execute()
    }

    fun updateToken(id: String, token: String) {
        context.update(PLAYER)
            .set(PLAYER.TOKEN, token)
            .where(PLAYER.ID.eq(id))
            .execute()
    }

    fun fetch(id: String): List<PlayerResponse> {
        return context.select(PLAYER.ID, PLAYER.NAME)
            .from(PLAYER)
            .where(PLAYER.ID.eq(id))
            .fetchInto(PlayerResponse::class.java)
    }

    fun fetchByName(name: String): List<PlayerResponse> {
        return context.select(PLAYER.ID, PLAYER.NAME)
            .from(PLAYER)
            .where(PLAYER.NAME.eq(name))
            .fetchInto(PlayerResponse::class.java)
    }

    fun fetchHydrated(id: String): List<Player> {
        return context.select(PLAYER.ID, PLAYER.NAME, PLAYER.TOKEN, PLAYER.SALT, PLAYER.PASSWORD)
            .from(PLAYER)
            .where(PLAYER.ID.eq(id))
            .fetchInto(Player::class.java)
    }

    fun fetchHydratedByName(name: String): List<Player> {
        return context.select(PLAYER.ID, PLAYER.NAME, PLAYER.PASSWORD, PLAYER.TOKEN, PLAYER.SALT)
            .from(PLAYER)
            .where(PLAYER.NAME.eq(name))
            .fetchInto(Player::class.java)
    }

    fun fetchHydratedByToken(token: String): List<Player> {
        return context.select(PLAYER.ID, PLAYER.NAME, PLAYER.PASSWORD, PLAYER.TOKEN, PLAYER.SALT)
            .from(PLAYER)
            .where(PLAYER.TOKEN.eq(token))
            .fetchInto(Player::class.java)
    }
}