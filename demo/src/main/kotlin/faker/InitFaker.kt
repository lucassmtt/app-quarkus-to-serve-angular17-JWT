package faker

import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@Startup
@ApplicationScoped
class InitFaker {

    @PostConstruct
    fun init() {
        System.out.println("The default of creation data is 30");

//        var numObjects = readNum("Users");

        UserDataFake().createUsersObj(40);
    }


    fun readNum(nameObj : String) : Int {
        val scanner = Scanner(System.`in`);

        val number : Int;

        try {

            print("Send the $nameObj objects number")
            number = scanner.nextInt();

            scanner.close();

        } catch (e : Exception) {
            return 1;
        }

        return number;

    }
}