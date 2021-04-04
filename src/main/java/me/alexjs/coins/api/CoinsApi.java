package me.alexjs.coins.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

// URI: /api
public interface CoinsApi {

    @PostMapping("/token")
    String createToken(@RequestHeader("Authorization") String auth);

}
