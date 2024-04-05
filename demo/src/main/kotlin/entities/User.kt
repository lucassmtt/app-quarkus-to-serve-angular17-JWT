package entities

import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.AuthProvider
import io.vertx.ext.auth.User
import io.vertx.ext.auth.authorization.Authorization
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity(name = "tb_user")
class User : User {

    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var name: String

    @Column(unique = true)
    lateinit var email: String

    @Column(unique = true)
    lateinit var username: String

    lateinit var password: String

    override fun attributes(): JsonObject {
        TODO("Not yet implemented")
    }

    override fun isAuthorized(p0: Authorization?, p1: Handler<AsyncResult<Boolean>>?): User {
        TODO("Not yet implemented")
    }

    override fun principal(): JsonObject {
        TODO("Not yet implemented")
    }

    override fun setAuthProvider(p0: AuthProvider?) {
        TODO("Not yet implemented")
    }

    override fun merge(p0: User?): User {
        TODO("Not yet implemented")
    }

}