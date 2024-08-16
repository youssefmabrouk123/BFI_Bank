package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.dto.UpdateUserRequest;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import com.twd.SpringSecurityJWT.service.AdminService;
import com.twd.SpringSecurityJWT.service.EmailService;
import com.twd.SpringSecurityJWT.service.OurUserService;
import com.twd.SpringSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
    @RequestMapping("/api/v1/users/public")

public class OurUserController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private OurUserRepo userRepository;
    @Autowired
    private AdminService adminService;


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

    @GetMapping("/email/{email}")
    public ResponseEntity<OurUsers> getUserByEmail(@PathVariable String email) {
        try {
            OurUsers user = userService.findByEmail(email);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/unblocked")
    public ResponseEntity<OurUsers> unblockUser(@RequestParam String email) {
        OurUsers user = adminService.unblockUserByEmail(email);


        // Optionally send an email notification
        // Envoyer un e-mail
        String subject = "Votre compte a été bloqué";
        String text = "Bonjour " + user.getEmail() + ",\n\nVotre compte a été bloqué. Veuillez contacter l'administrateur pour plus de détails.";
        emailService.sendEmail(user.getEmail(), subject, text);

        return ResponseEntity.ok(user);
    }


//    // API to set profile photo
//    @PostMapping("/{id}/profile-photo")
//    public ResponseEntity<String> setProfilePhoto(@PathVariable Integer id, @RequestParam("photo") MultipartFile photo) {
//        Optional<OurUsers> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            OurUsers user = optionalUser.get();
//            try {
//                user.setProfilePhoto(photo.getBytes());
//                userRepository.save(user);
//                return new ResponseEntity<>("Profile photo updated successfully", HttpStatus.OK);
//            } catch (IOException e) {
//                return new ResponseEntity<>("Failed to upload photo", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
////    // API to get profile photo
//    @GetMapping("/{id}/profile-photo")
//    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable Integer id) {
//        Optional<OurUsers> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            OurUsers user = optionalUser.get();
//            byte[] profilePhoto = user.getProfilePhoto();
//            if (profilePhoto != null) {
//                return new ResponseEntity<>(profilePhoto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}
