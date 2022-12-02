package hf.api.api

import hf.api.api.response.ErrorDescription
import hf.api.api.response.MessageResponse
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