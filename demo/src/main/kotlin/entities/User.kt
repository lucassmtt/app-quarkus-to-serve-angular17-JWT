package entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import repositories.UserRepository

@Inject
lateinit var userRepository : UserRepository;

@Entity
class User {

    @Id
    @GeneratedValue
    var id : Long? = null;

    lateinit var name : String

    lateinit var email : String

    lateinit var userName : String

}