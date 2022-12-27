package hf.api.game

import hf.api.app.AppFactory
import hf.api.app.HFService
import hf.api.app.ServiceUtils
import hf.api.cards.CardRepository
import hf.api.exception.IllegalDataStateException
import hf.api.exception.ItemNotFoundException
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
        val gameId = UUID.randomUUID().toString()
        val shoe = Shoe(
            id = UUID.randomUUID().toString(),
            size = shoeSize
        )
        val game = Game(
            id = gameId,
            shoe = shoe
        )

        gameRepository.create(game)
        shoeRepository.create(shoe, gameId)
        game.shoe.cards.forEach { cardPosition ->
            cardRepository.create(cardPosition.card)
            cardPositionRepository.create(cardPosition, shoe.id)
        }

        return gameId
    }

    fun nextCard(gameId: String): CardPosition {
        val currentPosition = ServiceUtils<ShoePosition>().validateUniqueInstance(shoeRepository.fetchNextPositionByGame(gameId))
        shoeRepository.updateNextPosition(nextPosition = currentPosition.position + 1, shoeId = currentPosition.shoeId)
        return ServiceUtils<CardPosition>().validateUniqueInstance(
            cardPositionRepository.fetchByShoePosition(
                ShoePosition(
                    shoeId = currentPosition.shoeId,
                    position = currentPosition.position
                )
            )
        )
    }
}