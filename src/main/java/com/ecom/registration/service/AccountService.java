package com.ecom.registration.service;


import com.ecom.registration.Repository.AccountRepository;
import com.ecom.registration.dto.*;
import com.ecom.registration.entity.Account;
import com.ecom.registration.entity.Partner;
import com.ecom.registration.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        // Check if the email already exists
        if (accountRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User already registered. Please login.");
        }
        Account account = req.getRole().equalsIgnoreCase("USER") ? new User() : new Partner();
        account.setEmail(req.getEmail());
        account.setPassword(passwordEncoder.encode(req.getPassword()));
        if (account instanceof Partner) {
            ((Partner) account).setBusinessType(req.getBusinessType());
        }
        accountRepository.save(account);
        return ResponseEntity.ok("Registered Successfully");
    }
    public ResponseEntity<String> login(LoginRequest req) {
        System.out.println("Login attempt: " + req.getEmail());

        Account acc = accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (acc != null && passwordEncoder.matches(req.getPassword(), acc.getPassword())) {
            return ResponseEntity.ok("Login Successful");
        } else {
            System.out.println("Login failed for: " + req.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    public ResponseEntity<String> resetPassword(PasswordResetRequest req) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(req.getEmail());

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Account acc = optionalAccount.get();
        acc.setPassword(passwordEncoder.encode(req.getNewPassword()));
        accountRepository.save(acc);

        return ResponseEntity.ok("Password has been reset successfully");
    }

    public ResponseEntity<String> fillUserDetails(UserDetailsRequest req) {
        Account acc = accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (acc instanceof User user) {
            user.setMobile(req.getMobile());
            user.setAge(req.getAge());
            accountRepository.save(user);
            return ResponseEntity.ok("User details updated");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    /*public ResponseEntity<String> fillPartnerDetails(PartnerDetailsRequest req) {
        Partner partner = (Partner) accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (partner != null) {
            partner.setBusinessType(req.getBusinessType());
            partner.setImage(req.getImage().getBytes());
            accountRepository.save(partner);
            return ResponseEntity.ok("Partner details updated");
        }
        return ResponseEntity.badRequest().body("Partner not found");
    }*/


}
