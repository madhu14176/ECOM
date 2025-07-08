package com.ecom.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDetailsRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Mobile number is required")
    private String mobile;

    @Min(value = 13, message = "Age must be at least 13")
    private int age;
}
