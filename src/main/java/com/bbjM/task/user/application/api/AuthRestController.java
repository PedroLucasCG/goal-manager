package com.bbjM.task.user.application.api;

import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AuthRestController implements AuthAPI {
    private AuthService authService;

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        log.info("[start] AuthRestController - createUser");
        log.info("[email] e-mail: {}", createUserRequest.getEmail());
        authService.registerUser(createUserRequest);
        log.debug("[finish] AuthRestController - createUser");
    }
}
