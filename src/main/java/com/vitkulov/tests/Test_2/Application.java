package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Набросаем пользователей с записями в базу для примера.
    @Bean
    public CommandLineRunner init(UserRepository userRepository) {
        return strings -> {
            LocalDateTime date = LocalDateTime.now();
            for (int i = 1; i <= 20; i++) {
                User user = new User("Пользователь " + i);
                for (int j = 1; j <= 10; j++) {
                    user.addRecord(new Record(date.plusDays(j), 1024L, 2048L, user));
                    user.addRecord(new Record(date.plusDays(j), 512L, 1024L, user));
                }
                userRepository.save(user);
            }
        };
    }
}
