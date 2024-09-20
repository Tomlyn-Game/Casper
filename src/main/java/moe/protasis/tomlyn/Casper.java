package moe.protasis.tomlyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Casper {
    public static void main(String[] args) {
        SpringApplication.run(Casper.class, args);
    }
}
