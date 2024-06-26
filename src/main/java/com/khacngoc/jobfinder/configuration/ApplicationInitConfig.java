package com.khacngoc.jobfinder.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khacngoc.jobfinder.Entity.User;
import com.khacngoc.jobfinder.Enums.Role;
import com.khacngoc.jobfinder.Repository.UserRepository;

@Configuration
public class ApplicationInitConfig {
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return arg -> {
            if (userRepository.findUserByEmail("admin").isEmpty()) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());
                userRepository.save(User.builder()
                        .email("admin")
                        .passWord(passwordEncoder.encode("admin"))
                        .role(roles)
                        .build());
            }

        };
    }

}
