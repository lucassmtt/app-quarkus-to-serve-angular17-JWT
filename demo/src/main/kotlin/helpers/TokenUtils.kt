import io.smallrye.jwt.build.Jwt
import jakarta.inject.Inject
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.jwt.JsonWebToken

class TokenUtils {

    @Inject
    lateinit var jwt: JsonWebToken

    fun generateToken(username: String): String {

        return Jwt.claims()
            .upn(username)
            .expiresAt(System.currentTimeMillis() / 1000 + 300) // Define time
            .sign()

    }

}
