package com.ecom.registration.controller;

import com.ecom.registration.Repository.AccountRepository;
import com.ecom.registration.dto.*;
import com.ecom.registration.entity.Account;
import com.ecom.registration.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return accountService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        Account acc = accountRepository.findByEmail(request.getEmail()).orElse(null);
        if (acc != null && passwordEncoder.matches(request.getPassword(), acc.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("role", acc.getRole());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> reset(@RequestBody PasswordResetRequest request) {
        return accountService.resetPassword(request);
    }

    @PostMapping("/user/details")
    public ResponseEntity<String> fillUserDetails(@RequestBody UserDetailsRequest request) {
        return accountService.fillUserDetails(request);
    }
/*
    @PostMapping("/partner/details")
    public ResponseEntity<String> fillPartnerDetails(@ModelAttribute PartnerDetailsRequest request) {
        return accountService.fillPartnerDetails(request);
    }*/
}
