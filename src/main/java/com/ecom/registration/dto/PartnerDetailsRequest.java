package com.ecom.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class PartnerDetailsRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Business type is required")
    private String businessType;

    private MultipartFile image; // ✅ for file upload
}
