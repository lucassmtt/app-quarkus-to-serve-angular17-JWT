package repositories

import entities.User
import io.quarkus.hibernate.orm.panache.Panache
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.Query
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

    fun findByPassword(password: String, user: User): Boolean {

        val sql = "SELECT u FROM tb_user u WHERE u.email = :email AND u.username = :username AND u.password = :password"

        val query: Query = Panache.getEntityManager().createQuery(sql)

        query.setParameter("email", user.email)
        query.setParameter("username", user.username)
        query.setParameter("password", user.password)

        val result = query.resultList

        return result.isNotEmpty()
    }
}