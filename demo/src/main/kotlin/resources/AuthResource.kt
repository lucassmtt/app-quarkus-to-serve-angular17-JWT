package resources

import TokenUtils
import jakarta.annotation.security.PermitAll
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
    @PermitAll
    fun login(usernameOrEmail: String, password: String): Response {

        var user: entities.User

        user = userService.findByUsernameOrEmail(usernameOrEmail)

        if (user != null) {

            if (userService.verifyPassword(user, password)) {

                val token = TokenUtils().generateToken(usernameOrEmail)

                return Response.ok(token).build()

            }

        }

        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

}