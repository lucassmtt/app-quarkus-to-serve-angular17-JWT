package faker

import com.github.javafaker.Faker
import entities.User
import jakarta.inject.Inject
import services.UserService
import java.util.Scanner

class UserDataFake {

    @Inject
    lateinit var userService : UserService;


    fun createUsersObj(numObjs : Int) {

        var numDefault : Int = 30;

        if (numObjs < 1000 || numObjs > 1) {
            numDefault = numObjs;
        }

        val faker = Faker();

        for (i in 1.. numDefault) {

            var user = User();

            user.userName = faker.name().username();
            user.email = faker.internet().emailAddress();
            user.name = faker.name().name();

            userService.createUser(user);

        }
    }

}