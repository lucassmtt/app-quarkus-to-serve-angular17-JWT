package repositories

import entities.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<User> {

    fun findByName(name : String) : PanacheQuery<User> = find("name", name)

    fun findByEmail(email : String) : PanacheQuery<User> {
        return super.find("email", email);
    }

    override fun findAll() : PanacheQuery<User> {
        return super.findAll();
    }

}