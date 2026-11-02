package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class AuthInfraRepository implements AuthRepository {
    private final AuthPostgresRepository authPostgresRepository;

    @Override
    public User createUser(User userFromRequest) {
        log.info("[start] AuthInfraRepository - createUser");
        User user = authPostgresRepository.save(userFromRequest);
        log.debug("[finish] AuthInfraRepository - createUser");
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        log.info("[start] AuthInfraRepository - findUserByEmail");
        Optional<User> user = authPostgresRepository.findUserByEmail(email);
        log.debug("[finish] AuthInfraRepository - findUserByEmail");
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        log.info("[start] AuthInfraRepository - findUserByUsername");
        Optional<User> user = authPostgresRepository.findUserByUsername(username);
        log.debug("[finish] AuthInfraRepository - findUserByUsername");
        return user;
    }
}
