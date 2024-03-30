package faker

import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped

@Startup
@ApplicationScoped
class InitFaker {

    @PostConstruct

    fun init() {



        UserDataFake().createUsersObj();
    }
}