package hf.api.integration

import hf.api.api.loginRequestLens
import hf.api.api.loginResponseLens
import hf.api.api.request.LoginRequest
import hf.api.app.AppFactory
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.AfterClass
import org.junit.BeforeClass

open class BaseSecureIntegrationTest : BaseIntegrationTest() {

    companion object {

        lateinit var token: String

        @AfterClass
        @JvmStatic
        fun teardown() {
            app.stop()
        }

        @BeforeClass
        @JvmStatic
        fun setup() {
            app.start()
            authenticate()
        }

        fun authenticate() {
            // Login
            token = loginResponseLens(
                client(
                    loginRequestLens(
                        LoginRequest(
                            name = "player1",
                            password = "Password1!"
                        ),
                        org.http4k.core.Request(Method.POST, "$baseUrl/login")
                    )
                )
            ).token
        }
    }

    val secureRequest = { method: Method, url: String ->
        Request(method, url).header(AppFactory.AUTHENTICATION_HEADER, token)
    }

}