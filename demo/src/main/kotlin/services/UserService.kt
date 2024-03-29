package services

import entities.User
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException
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

            return user;

        } else {

            throw BadRequestException("This email is already use.")

        }

    }
}