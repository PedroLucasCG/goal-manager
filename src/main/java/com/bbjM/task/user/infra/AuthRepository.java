package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.User;

import java.util.Optional;

public interface AuthRepository {

    User createUser(User userFromRequest);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
}
