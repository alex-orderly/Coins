package me.alexjs.coins.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.Principal;

// URI: /api
public interface CoinsApi {

    @PostMapping("/token")
    String createToken(Principal principal);

}
