package hf.api.api

import com.jondejong.hf.model.tables.pojos.Player
import hf.api.api.request.CreatePlayerRequest
import hf.api.api.response.ErrorDescription
import hf.api.api.response.ID
import hf.api.api.response.MessageResponse
import hf.api.api.response.PlayerResponse
import hf.api.cards.Card
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

// ID
val idLens = Body.auto<ID>().toLens()