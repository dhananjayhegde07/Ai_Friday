package com.ai.friday.DTOS.loginSignup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @NotNull
    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
