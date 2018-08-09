package space.efremov.otus.springmongodblibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "space.efremov.otus.springmongodblibrary")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
