/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package hf.api

import hf.api.api.cardListLens
import hf.api.app.AppFactory
import hf.api.properties.HFApiProperties
import hf.api.properties.ServerProperties
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.AfterClass
import org.junit.BeforeClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AppTest {
    companion object {

        private val testPort = 9999

        private val app = App(AppFactory(HFApiProperties(ServerProperties(port = testPort))))
        private val client = ApacheClient()

        @BeforeClass
        @JvmStatic
        fun setup() {
            app.start()
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            app.stop()
        }
    }

    @Test
    fun `can fetch a deck`() {
        val request = Request(Method.GET, "http://localhost:$testPort/deck")

        val resp = client(request)
        assertNotNull(resp)
        assertEquals(Status.OK, resp.status)

        val cards = cardListLens(resp)
        assertEquals(54, cards.size)
    }
}
