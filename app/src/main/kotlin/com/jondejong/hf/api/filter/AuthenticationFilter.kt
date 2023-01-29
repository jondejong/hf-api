import com.jondejong.hf.api.app.AppFactory
import com.jondejong.hf.api.app.HFConfigurable
import com.jondejong.hf.api.exception.ItemNotFoundException
import com.jondejong.hf.api.exception.UnauthenticatedException
import com.jondejong.hf.api.player.HydratedPlayerService
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.RequestContexts

class AuthenticationFilter : HFConfigurable {

    lateinit var playerService: HydratedPlayerService
    lateinit var contexts: RequestContexts

    override fun setUp(factory: AppFactory) {
        this.playerService = factory.hydratedPlayerService
        this.contexts = factory.contexts
    }

    val authenticationFilter = Filter { next: HttpHandler ->
        { request: Request ->
            val token = request.header(AppFactory.AUTHENTICATION_HEADER)
            if (token == null) {
                throw UnauthenticatedException()
            } else {
                try {
                    val player = playerService.fetchByToken(token)
                    contexts[request][AppFactory.AUTHENTICATED_USER] = player
                } catch (itemNotFoundException: ItemNotFoundException) {
                    throw UnauthenticatedException()
                }
            }
            next(request)
        }
    }
}