package com.ai.friday.DTOS.loginSignup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReq {
    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
            message = "Password must be 8-20 characters long, include uppercase, lowercase, number and special character"
    )
    private String password;

    @NotBlank
    @NotNull
    private String name;
}
