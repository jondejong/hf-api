package hf.api.integration

import hf.api.App
import hf.api.app.AppFactory
import hf.api.properties.DatabaseProperties
import hf.api.properties.HFApiProperties
import hf.api.properties.ServerProperties
import org.http4k.client.ApacheClient

abstract class BaseIntegrationTest {
    companion object {
        val testPort = 9999

        val testAppFactory = AppFactory(
            HFApiProperties(
                serverProperties = ServerProperties(
                    port = testPort
                ),
                //TODO: Get these from somewhere else
                DatabaseProperties(
                    url = "jdbc:postgresql://localhost:5432/hand_and_foot",
                    username = "hfapi",
                    password = "Password1!"
                )
            )
        )

        val baseUrl = "http://localhost:$testPort"

        val app = App(
            appFactory = testAppFactory
        )

        val client = ApacheClient()

    }
}