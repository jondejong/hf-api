package hf.api.game

import com.jondejong.hf.model.tables.Shoe.SHOE
import hf.api.app.HFRepository

class ShoeRepository : HFRepository() {

    fun create(shoe: Shoe, game: String) {
        context.insertInto(SHOE, SHOE.ID, SHOE.GAME, SHOE.NEXT_POSITION)
            .values(shoe.id, game, shoe.nextPosition)
            .execute()
    }
}