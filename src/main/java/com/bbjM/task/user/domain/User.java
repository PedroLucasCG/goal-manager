package com.bbjM.task.user.domain;

import com.bbjM.task.user.application.api.requests.CreateUserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    public User(CreateUserRequest createUserRequest) {
        username = createUserRequest.getUsername();
        password = createUserRequest.getPassword();
        email = createUserRequest.getEmail();
    }
}
