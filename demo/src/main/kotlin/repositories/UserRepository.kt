package repositories

import entities.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class UserRepository : PanacheRepository<User> {

    fun findByName(name : String) : PanacheQuery<User> = find("name", name)

    fun findByEmail(email : String) : User? = find("email", email).firstResult()

    fun findAll(sort : String) : List<User> = findAll(sort)

    fun deleteUser(id : Long) = deleteById(id)

    fun findByIdLong(id : Long) : User? {
        return findById(id)
    }

}