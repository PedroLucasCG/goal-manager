package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.Credential;

import java.util.Optional;

public interface AuthRepository {

    Credential createUser(Credential credentialFromRequest);

    Optional<Credential> findUserByEmail(String email);

    Optional<Credential> findUserByUsername(String username);
}
