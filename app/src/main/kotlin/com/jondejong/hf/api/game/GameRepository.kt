package com.jondejong.hf.api.game

import com.jondejong.hf.model.tables.Game.GAME
import com.jondejong.hf.api.app.HFRepository

class GameRepository : HFRepository() {

    fun create(game: Game) {
        context.insertInto(GAME, GAME.ID)
            .values(game.id)
            .execute()
    }
}