package com.twd.SpringSecurityJWT.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Service
public class VerificationService {

    private static final Logger LOGGER = Logger.getLogger(VerificationService.class.getName());
    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Map<String, Long> otpExpiry = new ConcurrentHashMap<>();
    private static final long OTP_VALIDITY_DURATION = 5 * 60 * 1000; // 5 minutes

    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(phoneNumber, otp);
        otpExpiry.put(phoneNumber, System.currentTimeMillis() + OTP_VALIDITY_DURATION);
        LOGGER.info("Generated OTP for " + phoneNumber + ": " + otp);
        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        String storedOtp = otpStorage.get(phoneNumber);
        Long expiryTime = otpExpiry.get(phoneNumber);
        if (storedOtp == null || expiryTime == null || System.currentTimeMillis() > expiryTime) {
            LOGGER.warning("OTP is invalid or expired for " + phoneNumber);
            otpStorage.remove(phoneNumber);
            otpExpiry.remove(phoneNumber);
            return false;
        }
        boolean isValid = storedOtp.equals(otp);
        if (isValid) {
            otpStorage.remove(phoneNumber);
            otpExpiry.remove(phoneNumber);
        }
        return isValid;
    }
}
