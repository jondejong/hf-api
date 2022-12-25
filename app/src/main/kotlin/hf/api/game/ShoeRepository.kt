package hf.api.game

import com.jondejong.hf.model.tables.Shoe.SHOE
import hf.api.app.HFRepository

class ShoeRepository : HFRepository() {

    fun create(shoe: Shoe, game: String) {
        context.insertInto(SHOE, SHOE.ID, SHOE.GAME, SHOE.NEXT_POSITION)
            .values(shoe.id, game, shoe.nextPosition)
            .execute()
    }

    fun fetchNextPositionByGame(gameId: String): List<ShoePosition> {
        return context.selectFrom(SHOE)
            .where(SHOE.GAME.eq(gameId))
            .fetch()
            .map { shoeRecord ->
                ShoePosition(
                    shoeId = shoeRecord.id,
                    position = shoeRecord.nextPosition
                )
            }
    }

    fun updateNextPosition(shoeId: String, nextPosition: Int) {
        context.update(SHOE)
            .set(SHOE.NEXT_POSITION, nextPosition)
            .where(SHOE.ID.eq(shoeId))
            .execute()
    }
}