package com.ecom.registration.service;


import com.ecom.registration.Repository.AccountRepository;
import com.ecom.registration.dto.*;
import com.ecom.registration.entity.Account;
import com.ecom.registration.entity.Partner;
import com.ecom.registration.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(RegisterRequest req) {
        Account account = req.getRole().equalsIgnoreCase("USER") ? new User() : new Partner();
        account.setEmail(req.getEmail());
        account.setPassword(passwordEncoder.encode(req.getPassword()));
        accountRepository.save(account);
        return ResponseEntity.ok("Registered Successfully");
    }

    public ResponseEntity<String> login(LoginRequest req) {
        Account acc = accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (acc != null && passwordEncoder.matches(req.getPassword(), acc.getPassword())) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public ResponseEntity<String> resetPassword(PasswordResetRequest req) {
        Account acc = accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (acc != null) {
            acc.setPassword(passwordEncoder.encode(req.getNewPassword()));
            accountRepository.save(acc);
            return ResponseEntity.ok("Password reset");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    public ResponseEntity<String> fillUserDetails(UserDetailsRequest req) {
        User user = (User) accountRepository.findByEmail(req.getEmail()).orElse(null);
        if (user != null) {
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
