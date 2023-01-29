package com.jondejong.hf.api.api

import com.jondejong.hf.api.api.request.CreateGameRequest
import com.jondejong.hf.api.api.request.CreatePlayerRequest
import com.jondejong.hf.api.api.request.LoginRequest
import com.jondejong.hf.api.api.response.*
import com.jondejong.hf.api.cards.Card
import com.jondejong.hf.api.game.CardPosition
import org.http4k.core.Body
import org.http4k.format.Jackson.auto

// Defaults
val messageLens = Body.auto<MessageResponse>().toLens()

// Error
val errorDescriptionLens = Body.auto<ErrorDescription>().toLens()

// Card
val cardLens = Body.auto<Card>().toLens()
val cardListLens = Body.auto<List<Card>>().toLens()

// Player
val playerLens = Body.auto<PlayerResponse>().toLens()
val playerListLens = Body.auto<List<PlayerResponse>>().toLens()
val createPlayerLens = Body.auto<CreatePlayerRequest>().toLens()
val loginRequestLens = Body.auto<LoginRequest>().toLens()
val loginResponseLens = Body.auto<LoginResponse>().toLens()

// Game
val createGameLens = Body.auto<CreateGameRequest>().toLens()
val cardPositionLens = Body.auto<CardPosition>().toLens()

// ID
val idLens = Body.auto<ID>().toLens()