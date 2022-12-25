package hf.api.integration

import hf.api.api.cardPositionLens
import hf.api.api.createGameLens
import hf.api.api.idLens
import hf.api.api.request.CreateGameRequest
import org.http4k.core.Method
import org.http4k.core.Status
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GameTest : BaseSecureIntegrationTest() {

    fun createGame(): UUID {
        val createResponse = client(
            createGameLens(
                CreateGameRequest(6),
                secureRequest(Method.POST, "$baseUrl/games")
            )
        )
        assertEquals(Status.OK, createResponse.status)
        return idLens(createResponse).id
    }

    @Test
    fun `can create a game`() {
        assertNotNull(createGame())
    }

    @Test
    fun `can get next card on new game`() {
        val gameId = createGame().toString()
        val url = "$baseUrl/games/$gameId/next"
        val cardPosition = cardPositionLens(
            client(secureRequest(Method.GET, "$baseUrl/games/$gameId/next"))
        )

        val secondCardPosition = cardPositionLens(
            client(secureRequest(Method.GET, "$baseUrl/games/$gameId/next"))
        )

        assertEquals(cardPosition.position, 0)
        assertEquals(secondCardPosition.position, 1)
    }
}