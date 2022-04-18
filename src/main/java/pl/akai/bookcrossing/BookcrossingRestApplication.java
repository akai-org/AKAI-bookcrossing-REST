package pl.akai.bookcrossing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class BookcrossingRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookcrossingRestApplication.class, args);
    }

}
