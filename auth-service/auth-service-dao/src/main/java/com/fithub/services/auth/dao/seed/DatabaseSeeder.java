package com.fithub.services.auth.dao.seed;

import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fithub.services.auth.dao.model.CoachEntity;
import com.fithub.services.auth.dao.model.UserEntity;
import com.fithub.services.auth.dao.repository.CoachRepository;
import com.fithub.services.auth.dao.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements ApplicationRunner {

    private final CoachRepository coachRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seed();
    }

    private void seed() {
        UserEntity userEntity = new UserEntity();
        if (userRepository.findAll().isEmpty()) {
            userEntity.setUuid(UUID.randomUUID().toString());
            userEntity.setFirstName("John");
            userEntity.setLastName("Doe");
            userEntity.setUsername("johndoe");
            userEntity.setEmail("johndoe@email.com");
            userEntity.setPasswordHash("password123");
            userRepository.save(userEntity);
        }

        CoachEntity coachEntity = new CoachEntity();
        if (coachRepository.findAll().isEmpty()) {
            coachEntity.setUser(userEntity);
            coachEntity.setBiography("John Doe is the head coach.");
            coachRepository.save(coachEntity);
        }
    }

}