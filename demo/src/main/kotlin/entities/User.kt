package entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity(name = "tb_user")
class User {

    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var name: String

    lateinit var email: String

    lateinit var userName: String

}