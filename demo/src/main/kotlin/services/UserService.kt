package services

import TokenUtils
import entities.User
import io.vertx.codegen.doc.Token
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.Response
import repositories.UserRepository

@ApplicationScoped
class UserService {

    @Inject
    lateinit var manager : EntityManager;

    @Inject
    lateinit var userRepository: UserRepository;

    @Transactional
    fun createUser(user : User) : Any {

        if (userRepository.findByEmail(user.email) == null) {

            manager.persist(user);

            val token = TokenUtils()

            if (user.username === null) {
                val token = TokenUtils().generateToken(user.username)
            } else {
                val token = TokenUtils().generateToken(user.email)
            }

            return Response.ok(token).build()

        } else {

            throw BadRequestException("This email is already use.")

        }

    }

    fun  findByUsernameOrEmail(usernameOrEmail: String): User {

        var user: User

        if (usernameOrEmail.contains("@")) {
            user = userRepository.findByEmail(usernameOrEmail)!!;
        } else {
            user = userRepository.findByUsername(usernameOrEmail)!!;
        }

        return user;
    }

    fun verifyPassword(user: User, password: String): Boolean = userRepository.findByPassword(user, password)

}