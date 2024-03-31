package resources

import com.google.gson.Gson
import entities.User
import entities.dto.UserDto
import helpers.JsonConverter
import helpers.ResponseBadRequest
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.jboss.logging.annotations.Param
import repositories.UserRepository
import services.UserService

@Path("user")
class UserResource {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userService: UserService
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): String? {
        var users: List<User>;

        users = userRepository.findAll().list();

        val usersDto = users.map { user -> UserDto(user.username, user.email, user.name) }

        return Gson().toJson(usersDto);
    }

    @GET
    @Path("/{id}")
    fun getById(id : Long) : Any {
        var user = userRepository.findByIdLong(id);

        if (user !== null) {
            return user;
        } else {
            return ResponseBadRequest()
        }
    }

    @Path("/add/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addUser(@Param json: String): Any? {
        var user = Gson().fromJson(json, User::class.java);

        if (user.id === null) {
            // Create
            user = userService.createUser(user) as User?
        } else {
            // Update
            userService.createUser(user);
        }

        return Response.ok(user);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateUser(@PathParam ("id") id: Long, updatedUser: User): Response {
        val existingUser = userRepository.findById(id)
        if (existingUser != null) {
            existingUser.apply {
                name = updatedUser.name
                email = updatedUser.email
                username = updatedUser.username
            }
            userRepository.persist(existingUser)
            return Response.ok(existingUser).build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteUser(@PathParam ("id") id: Long): Response {
        val existingUser = userRepository.findById(id)
        if (existingUser != null) {
            userRepository.deleteUser(id)
            return Response.noContent().build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).build()
        }
    }

}