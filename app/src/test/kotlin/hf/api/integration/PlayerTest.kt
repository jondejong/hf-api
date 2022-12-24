package hf.api.integration

import hf.api.api.createPlayerLens
import hf.api.api.idLens
import hf.api.api.playerLens
import hf.api.api.playerListLens
import hf.api.api.request.CreatePlayerRequest
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PlayerTest : BaseIntegrationTest() {

    @Test
    fun `can create players`() {

        val baseRequest = Request(Method.GET, "http://localhost:$testPort/players")

        val resp = client(baseRequest)
        assertNotNull(resp)
        assertEquals(Status.OK, resp.status)

        val players = playerListLens(resp)
        var numberOfPlayers = players.size

        val createResponse = client(
            createPlayerLens(
                CreatePlayerRequest("test-user", "Password1!"),
                Request(Method.POST, "http://localhost:$testPort/players")
            )
        )

        assertEquals(Status.OK, createResponse.status)
        val id = idLens(createResponse)

        val secondGetResponse = client(baseRequest)
        assertNotNull(secondGetResponse)
        assertEquals(Status.OK, secondGetResponse.status)

        val secondListOfPlayers = playerListLens(secondGetResponse)
        assertEquals(numberOfPlayers + 1, secondListOfPlayers.size)

        val fetchResponse = client(Request(Method.GET, "http://localhost:$testPort/players/${id.id}"))
        assertNotNull(fetchResponse)
        assertEquals(Status.OK, fetchResponse.status)
        val player = playerLens(fetchResponse).name
        assertEquals("test-user", playerLens(fetchResponse).name)
    }
}