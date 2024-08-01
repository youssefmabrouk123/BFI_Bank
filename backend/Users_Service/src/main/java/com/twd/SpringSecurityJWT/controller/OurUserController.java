package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.service.OurUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/users/public/api/users")

public class OurUserController {

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
}
