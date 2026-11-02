package com.bbjM.task.user.infra;

import com.bbjM.task.user.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthPostgresRepository extends JpaRepository<Credential, UUID> {
    Optional<Credential> findUserByEmail(String email);

    Optional<Credential> findUserByUsername(String username);
}
