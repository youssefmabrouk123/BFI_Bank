package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OurUserService {

    private static final Logger LOGGER = Logger.getLogger(OurUserService.class.getName());

    @Autowired
    private OurUserRepo ourUserRepository;

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private VerificationService verificationService;

    public OurUsers saveUser(OurUsers user) {
        return ourUserRepository.save(user);
    }

    public String sendOtpForPhoneNumber(String phoneNumber) {
        Optional<OurUsers> userOptional = ourUserRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            String otp = verificationService.generateOtp(phoneNumber);
            twilioService.sendSms(phoneNumber, "Your verification code is: " + otp);
            return "OTP sent successfully!";
        } else {
            return "User with this phone number does not exist.";
        }
    }

    public String verifyOtpForPhoneNumber(String phoneNumber, String otp) {
        Optional<OurUsers> userOptional = ourUserRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            boolean isVerified = verificationService.verifyOtp(phoneNumber, otp);
            if (isVerified) {
                OurUsers user = userOptional.get();
                user.setPhoneNumberVerified(true);
                ourUserRepository.save(user);
                return "Phone number verified successfully!";
            } else {
                return "Invalid OTP!";
            }
        } else {
            return "User with this phone number does not exist.";
        }
    }
}
