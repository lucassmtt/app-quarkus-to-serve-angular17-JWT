package faker

import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import repositories.UserRepository
import services.UserService
import java.util.*

@Startup
@ApplicationScoped
class InitFaker {

    @Inject
    lateinit var userService: UserService;

    @Inject
    lateinit var userRepository: UserRepository;

    @PostConstruct
    fun init() {
        System.out.println("The default of creation data is 30");

//        var numObjects = readNum("Users");
        if (userRepository.findAll().list().size <= 500) {
            UserDataFake(userService).createUsersObj(40);
        } else {
            println("Database is getting very full");
        }
    }


    fun readNum(nameObj: String): Int {
        val scanner = Scanner(System.`in`);

        val number: Int;

        try {

            print("Send the $nameObj objects number")
            number = scanner.nextInt();

            scanner.close();

        } catch (e: Exception) {
            return 1;
        }

        return number;

    }
}