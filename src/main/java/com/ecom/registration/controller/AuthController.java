package com.ecom.registration.controller;

import com.ecom.registration.dto.*;
import com.ecom.registration.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return accountService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return accountService.login(request);
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
