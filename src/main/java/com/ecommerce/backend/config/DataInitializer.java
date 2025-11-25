package com.ecommerce.backend.config;

import com.ecommerce.backend.domain.User;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByName("testuser").isEmpty()) {
            User testUser = new User();
            testUser.setName("testuser");
            testUser.setPassword(passwordEncoder.encode("password"));


            testUser.setRoles(Collections.singletonList("ROLE_USER"));

            userRepository.save(testUser);
        }
    }
}