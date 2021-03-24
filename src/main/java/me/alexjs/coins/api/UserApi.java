package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateUserRequest;
import me.alexjs.coins.response.CreateUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {

    @PostMapping("/create")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);

}
