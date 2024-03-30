package faker

import com.github.javafaker.Faker
import entities.User
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import services.UserService

@Startup
@ApplicationScoped
class UserDataFake @Inject constructor(var userService: UserService) {

    fun createUsersObj(numObjs: Int) {

        var numDefault: Int = 30

        if (numObjs < 1000 || numObjs > 1) {
            numDefault = numObjs
        }

        val faker = Faker()

        for (i in 1..numDefault) {

            var user = User()

            user.userName = faker.name().username()
            user.email = faker.internet().emailAddress()
            user.name = faker.name().name()

            userService.createUser(user)

        }

    }

    fun createManager(): EntityManager {
        val entityManagerFactory: EntityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit")

        val manager = entityManagerFactory.createEntityManager()

        return manager
    }

}