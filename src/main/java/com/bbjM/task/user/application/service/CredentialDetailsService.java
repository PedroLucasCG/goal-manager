package com.bbjM.task.user.application.service;

import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.user.domain.Credential;
import com.bbjM.task.user.infra.AuthInfraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor()
@Log4j2
public class CredentialDetailsService implements UserDetailsService {
    private final AuthInfraRepository authInfraRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[start] CredentialDetailsService - loadUserByUsername");
        Credential credential = authInfraRepository.findUserByEmail(email)
                .orElseThrow(() -> new APIException(ErrorCode.CREDENTIAL_NOT_FOUND));
        UserDetails userDetails = User.withUsername(credential.getEmail())
                .password(credential.getPassword())
                .roles(credential.getRole().name())
                .disabled(credential.getDeleted())
                .build();
        log.debug("[finish] CredentialDetailsService - loadUserByUsername");
        return userDetails;
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
