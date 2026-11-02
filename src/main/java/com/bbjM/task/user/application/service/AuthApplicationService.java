package com.bbjM.task.user.application.service;

import com.bbjM.task.user.application.api.AuthAPI;
import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.domain.User;
import com.bbjM.task.user.infra.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class AuthApplicationService implements AuthService {
    private AuthRepository authRepository;

    @Override
    public User registerUser(CreateUserRequest createUserRequest) {
        log.info("[start] AuthApplicationService - registerUser");
        User userFromRequest = new User(createUserRequest);
        User user = authRepository.createUser(userFromRequest);
        log.debug("[finish] AuthApplicationService - registerUser");
        return user;
    }
}
