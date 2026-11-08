package com.bbjM.task.user.domain;

import com.bbjM.task.shared.Auditable;
import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Credential extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING) private Role role;
    @Column(nullable = false)
    private Boolean deleted;

    public Credential(CreateUserRequest createUserRequest) {
        username = createUserRequest.getUsername();
        email = createUserRequest.getEmail().toLowerCase();
        deleted = false;
        role = Role.USER;
    }

    public void makePasswordFromRequest(PasswordEncoder passwordEncoder, CreateUserRequest createUserRequest) {
        this.password = passwordEncoder.encode(createUserRequest.getPassword());
    }

}
