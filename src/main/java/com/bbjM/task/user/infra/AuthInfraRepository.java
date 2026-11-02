package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.Credential;
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
    public Credential createUser(Credential credentialFromRequest) {
        log.info("[start] AuthInfraRepository - createUser");
        Credential credential = authPostgresRepository.save(credentialFromRequest);
        log.debug("[finish] AuthInfraRepository - createUser");
        return credential;
    }

    @Override
    public Optional<Credential> findUserByEmail(String email) {
        log.info("[start] AuthInfraRepository - findUserByEmail");
        Optional<Credential> user = authPostgresRepository.findUserByEmail(email);
        log.debug("[finish] AuthInfraRepository - findUserByEmail");
        return user;
    }

    @Override
    public Optional<Credential> findUserByUsername(String username) {
        log.info("[start] AuthInfraRepository - findUserByUsername");
        Optional<Credential> user = authPostgresRepository.findUserByUsername(username);
        log.debug("[finish] AuthInfraRepository - findUserByUsername");
        return user;
    }
}
