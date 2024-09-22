package moe.protasis.casper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CasperServer {
    public static void main(String[] args) {
        SpringApplication.run(CasperServer.class, args);
    }
}
