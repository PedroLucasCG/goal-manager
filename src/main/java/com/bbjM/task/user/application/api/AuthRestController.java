package com.bbjM.task.user.application.api;

import com.bbjM.task.user.application.api.reponses.LoginResponse;
import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.application.api.requests.LoginRequest;
import com.bbjM.task.user.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AuthRestController implements AuthAPI {
    private final AuthService authService;

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        log.info("[start] AuthRestController - createUser");
        log.info("[email] e-mail: {}", createUserRequest.getEmail());
        authService.registerUser(createUserRequest);
        log.debug("[finish] AuthRestController - createUser");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("[start] AuthRestController - login");
        log.info("[email] e-mail: {}", loginRequest.getEmail());
        LoginResponse loginResponse = authService.login(loginRequest);
        log.debug("[finish] AuthRestController - login");
        return loginResponse;
    }
}
