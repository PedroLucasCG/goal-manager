package com.bbjM.task.user.application.api;

import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public interface AuthAPI {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody CreateUserRequest createUserRequest);
}
