package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class AuthInfraRepository implements AuthRepository {
    @Override
    public User createUser(User userFromRequest) {
        return null;
    }
}
