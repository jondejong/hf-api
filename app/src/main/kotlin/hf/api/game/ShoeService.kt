package hf.api.game

import hf.api.app.AppFactory
import hf.api.app.HFService

class ShoeService : HFService<Shoe>() {

    private lateinit var shoeRepository: ShoeRepository

    override fun setUp(factory: AppFactory) {
        this.shoeRepository = factory.shoeRepository
    }

    fun create(shoe: Shoe, game: String) {
        shoeRepository.create(shoe, game)
    }

//    fun fetchNextPositionByGame(gameId: String) : Int {
//        return validateUniqueInstance(shoeRepository.fetchNextPositionByGame(gameId))
//    }
}