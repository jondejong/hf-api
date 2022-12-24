package hf.api.game

import hf.api.app.AppFactory
import hf.api.app.HFService
import hf.api.cards.CardRepository
import java.util.*

class GameService : HFService<Game>() {

    lateinit var gameRepository: GameRepository
    lateinit var cardPositionRepository: CardPositionRepository
    lateinit var cardRepository: CardRepository
    lateinit var shoeRepository: ShoeRepository

    override fun setUp(factory: AppFactory) {
        gameRepository = factory.gameRepository
        cardPositionRepository = factory.cardPositionRepository
        cardRepository = factory.cardRepository
        shoeRepository = factory.shoeRepository
    }

    fun create(shoeSize: Int): String {
        val id = UUID.randomUUID().toString()
        val shoe = Shoe(
            id = UUID.randomUUID().toString(),
            size = shoeSize
        )
        val game = Game(
            id = id,
            shoe = shoe
        )

        gameRepository.create(game)
        shoeRepository.create(shoe, id)
        game.shoe.cards.forEach { cardPosition ->
            cardRepository.create(cardPosition.card)
            cardPositionRepository.create(cardPosition, shoe.id)
        }

        return id
    }
}