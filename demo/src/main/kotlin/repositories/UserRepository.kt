package repositories

import entities.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
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

    fun findByUsername(username: String): User? = find("username", username).firstResult()

    fun findByPassword(password: String, user: User) {
        var sql: String = "select u from tb_user u where u.email = ?1 and u.username = ?2 and u.password = ?3";

        return find(query = sql, params = user.email, user.username, user.password)

    }
}