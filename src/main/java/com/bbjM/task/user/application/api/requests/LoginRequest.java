package com.bbjM.task.user.application.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "O email não pode ser nulo")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>\\[\\]\\\\/~`_+=;'\\-]).{8,}$",
            message = "A senha deve conter ao menos uma letra maiúscula, uma letra minúscula, " +
                    "um caractere especial e ter no mínimo 8 caracteres"
    )
    private String password;
}
