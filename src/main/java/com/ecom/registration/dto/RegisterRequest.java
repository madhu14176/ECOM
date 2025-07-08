package com.ecom.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String role; // USER or PARTNER

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
