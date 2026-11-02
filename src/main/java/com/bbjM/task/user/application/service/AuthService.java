package com.bbjM.task.user.application.service;

import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.domain.Credential;

public interface AuthService {

    Credential registerUser(CreateUserRequest createUserRequest);
}
