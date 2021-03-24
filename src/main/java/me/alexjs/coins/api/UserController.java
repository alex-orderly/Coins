package me.alexjs.coins.api;

import me.alexjs.coins.db.UserDbo;
import me.alexjs.coins.request.CreateUserRequest;
import me.alexjs.coins.response.CreateUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        log.info("Received request: CreateUserRequest");

        UserDbo user = new UserDbo(
                request.getFirstName(),
                request.getLastName(),
                request.getUserName(),
                request.getBase64HashedPassword());

//        session.persist(user);

        return new CreateUserResponse(HttpStatus.OK);
    }

}
