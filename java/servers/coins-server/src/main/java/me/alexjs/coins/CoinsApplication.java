package me.alexjs.coins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "me.alexjs")
public class CoinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinsApplication.class, args);
    }

}
