package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/email")
public class EmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @GetMapping("/sendVerificationToken")
    public ResponseEntity<String> sendVerificationToken(@RequestParam String email) {
        OurUsers user = emailVerificationService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with email: " + email);
        }

        emailVerificationService.sendVerificationEmail(user);
        return ResponseEntity.ok("Verification token sent to: " + email);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean isVerified = emailVerificationService.verifyEmailToken(token);
        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification token.");
        }
    }
}
