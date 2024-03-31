package resources

import io.vertx.ext.auth.User
import jakarta.inject.Inject
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken
import repositories.UserRepository
import services.UserService

@Path("auth")
class AuthResource {

    @Inject
    lateinit var jwt: JsonWebToken

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userService: UserService

    @POST
    @Path("/login")
    fun login(usernameOrEmail: String, password: String): Response {

        var user: entities.User

        user = userService.findByUsernameOrEmail(usernameOrEmail)

        if (user != null) {
            userService.verifiyPassword(user, password)

            val token = generateToken(user)

            return Response.ok(token).build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


}