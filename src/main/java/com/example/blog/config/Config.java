package com.example.blog.config;

import com.example.blog.domain.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")  // test bcs no application.properties esta
public class Config implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user1 = new User(null, "John", "Doe" );
        User user2 = new User(null, "Jane", "Doe" );
        User user3 = new User(null, "Marie", "Smith" );

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


    }
}
