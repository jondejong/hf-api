package com.jondejong.hf.api.player

import com.jondejong.hf.api.api.loginRequestLens
import com.jondejong.hf.api.api.loginResponseLens
import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFConfigurable
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind

class LoginRoutes : HFConfigurable {

    private lateinit var loginService: LoginService

    override fun setUp(factory: AppFactory) {
        this.loginService = factory.loginService
    }

    private val loginHandler = { request: Request ->
        val login = loginRequestLens(request)
        val response = loginService.validateUser(login.name, login.password)
        loginResponseLens(response, Response(Status.OK))
    }

    val routes = org.http4k.routing.routes(
        "/" bind Method.POST to loginHandler,
    )
}