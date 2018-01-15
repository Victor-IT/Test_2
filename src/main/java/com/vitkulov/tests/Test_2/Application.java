package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.model.DataRecord;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository) {
        return strings -> {
            for (int i = 1; i < 21; i++) {
                User user = new User("Пользователь " + i);
                user.addDataRecord(new DataRecord(1024L, 2048L, user));
                user.addDataRecord(new DataRecord(512L, 1024L, user));
                userRepository.save(user);
            }
        };
    }
}
