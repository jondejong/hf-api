package com.jondejong.hf.api.filter

import com.jondejong.hf.api.api.errorDescriptionLens
import com.jondejong.hf.api.api.response.ErrorDescription
import com.jondejong.hf.api.api.response.ErrorMessages
import com.jondejong.hf.api.exception.IllegalDataStateException
import com.jondejong.hf.api.exception.ItemNotFoundException
import com.jondejong.hf.api.exception.RequestValidationException
import com.jondejong.hf.api.exception.UnauthenticatedException
import org.http4k.core.*

object ErrorHandlerFilter {
    val errorFilter = Filter { next: HttpHandler ->
        { request: Request ->
            var response: Response
            try {
                response = next(request)
            } catch (e: ItemNotFoundException) {
                response = Response(Status.NOT_FOUND)
            } catch (e: IllegalArgumentException) {
                response = Response(Status.BAD_REQUEST)
            } catch (e: IllegalDataStateException) {
                response = Response(Status.BAD_REQUEST)
            } catch (e: UnauthenticatedException) {
                response = Response(Status.UNAUTHORIZED)
            } catch (e: RequestValidationException) {
                response = errorDescriptionLens(
                    ErrorDescription(ErrorMessages(e.messages)),
                    Response(Status.UNPROCESSABLE_ENTITY)
                )
            } catch (t: Throwable) {
                response = Response(Status.INTERNAL_SERVER_ERROR)
            }
            response
        }
    }
}