package com.bbjM.task.user.application.service;

import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.domain.Credential;
import com.bbjM.task.user.infra.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class AuthApplicationService implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public Credential registerUser(CreateUserRequest createUserRequest) {
        log.info("[start] AuthApplicationService - registerUser");
        checkIfUserExists(createUserRequest);
        Credential credentialFromRequest = new Credential(createUserRequest);
        Credential credential = authRepository.createUser(credentialFromRequest);
        log.debug("[finish] AuthApplicationService - registerUser");
        return credential;
    }

    private void checkIfUserExists(CreateUserRequest createUserRequest) {
        log.info("[start] AuthApplicationService - checkIfUserExists");
        getUserByEmail(createUserRequest.getEmail())
                .ifPresent(user -> {
                    throw new APIException(ErrorCode.EMAIL_ALREADY_USED, createUserRequest.getEmail());
                });
        getUserByUsername(createUserRequest.getUsername())
                .ifPresent(user -> {
                    throw new APIException(ErrorCode.USERNAME_ALREADY_USED, createUserRequest.getUsername());
                });
        log.debug("[finish] AuthApplicationService - checkIfUserExists");
    }

    private Optional<Credential> getUserByEmail(String email) {
        log.info("[start] AuthApplicationService - getUserByEmail");
        Optional<Credential> userFromDB = authRepository.findUserByEmail(email);
        log.debug("[finish] AuthApplicationService - getUserByEmail");
        return userFromDB;
    }

    private Optional<Credential> getUserByUsername(String username) {
        log.info("[start] AuthApplicationService - getUserByUsername");
        Optional<Credential> userFromDB = authRepository.findUserByUsername(username);
        log.debug("[finish] AuthApplicationService - getUserByUsername");
        return userFromDB;
    }
}
