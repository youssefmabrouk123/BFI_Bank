package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.dto.UpdateUserRequest;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import com.twd.SpringSecurityJWT.service.OurUserService;
import com.twd.SpringSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
    @RequestMapping("/api/v1/users/public")

public class OurUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OurUserRepo userRepository;

    private static final Logger LOGGER = Logger.getLogger(OurUserController.class.getName());

    @Autowired
    private OurUserService ourUserService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody Map<String, String> requestBody) {
        String phoneNumber = requestBody.get("phoneNumber");
        LOGGER.info("Request received to send OTP to " + phoneNumber);
        return ourUserService.sendOtpForPhoneNumber(phoneNumber);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String, String> requestBody) {
        String phoneNumber = requestBody.get("phoneNumber");
        String otp = requestBody.get("otp");
        LOGGER.info("Request received to verify OTP for " + phoneNumber);
        return ourUserService.verifyOtpForPhoneNumber(phoneNumber, otp);
    }


    @GetMapping("/user")
    public OurUsers getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserByMail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }





}
