package resources

import com.google.gson.Gson
import entities.User
import helpers.ResponseBadRequest
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.jboss.logging.annotations.Param
import repositories.UserRepository

@Path("user")
class UserResource {

    @Inject
    lateinit var userRepository: UserRepository

    @GET
    fun getAll(): List<User> {
        var users: PanacheQuery<User> = userRepository.findAll();

        return users.list();
    }

    @Path("/add/{json}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun addUser(@Param json: String): Any? {
        var user = Gson().fromJson(json, User::class.java);

        if (user.userName != null && user.name != null && user.email != null) {

            userRepository.persist(user);

            return Response.ok(user);

        } else {
            return ResponseBadRequest().resourceNotCreateResponse();
        }
    }

}