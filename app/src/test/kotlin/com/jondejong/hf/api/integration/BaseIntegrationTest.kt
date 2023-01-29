package com.jondejong.hf.api.integration

import com.jondejong.hf.api.App
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.properties.DatabaseProperties
import com.jondejong.hf.api.properties.HFApiProperties
import com.jondejong.hf.api.properties.ServerProperties
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

        val app = com.jondejong.hf.api.App(
            appFactory = testAppFactory
        )

        val client = ApacheClient()

    }
}