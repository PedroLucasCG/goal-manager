package com.bbjM.task.user.application.service;

import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import com.bbjM.task.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User registerUser(CreateUserRequest createUserRequest);
}
