package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.User;

public interface AuthRepository {

    User createUser(User userFromRequest);
}
