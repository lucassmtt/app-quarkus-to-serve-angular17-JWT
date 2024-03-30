package faker

import com.github.javafaker.Faker
import entities.User
import jakarta.inject.Inject
import services.UserService

class UserDataFake {

    @Inject
    lateinit var userService : UserService;

    fun createUsersObjWithoutCount() {

        System.out.println("The default of creation data is 30");

        createUsersObj(30);
    }


    fun createUsersObj(numObjs : Int) {

        val faker = Faker();

        for (i in 1.. numObjs) {

            var user = User();

            user.userName = faker.name().username();
            user.email = faker.internet().emailAddress();
            user.name = faker.name().name();

            userService.createUser(user);

        }
    }


}