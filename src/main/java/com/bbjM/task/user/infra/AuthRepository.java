package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository {

    User createUser(User userFromRequest);
}
