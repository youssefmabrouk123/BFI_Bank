//package com.twd.SpringSecurityJWT.controller;
//
//import com.twd.SpringSecurityJWT.entity.OurUsers;
//import com.twd.SpringSecurityJWT.service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @PutMapping("/unblock/{userId}")
//    public ResponseEntity<OurUsers> unblockUser(@PathVariable Integer userId) {
//        OurUsers user = adminService.unblockUser(userId);
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping("/block/{userId}")
//    public ResponseEntity<OurUsers> blockUser(@PathVariable Integer userId) {
//        OurUsers user = adminService.blockUser(userId);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<OurUsers> getUser(@PathVariable Integer userId) {
//        OurUsers user = adminService.getUser(userId);
//        return ResponseEntity.ok(user);
//    }
//}


package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.service.AdminService;
import com.twd.SpringSecurityJWT.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    @PutMapping("/unblock/{userId}")
    public ResponseEntity<OurUsers> unblockUser(@PathVariable Integer userId) {
        OurUsers user = adminService.unblockUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<OurUsers> blockUser(@PathVariable Integer userId) {
        OurUsers user = adminService.blockUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<OurUsers> getUser(@PathVariable Integer userId) {
        OurUsers user = adminService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/unblocked")
    public ResponseEntity<OurUsers> unblockUser(@RequestParam String email) {
        OurUsers user = adminService.unblockUserByEmail(email);

        // Optionally send an email notification
        if (user != null) {
            String emailSubject = "Your Account has been Unblocked";
            String emailBody = "Dear " + user.getNom() + ",\n\n" +
                    "Your account has been successfully unblocked. You can now log in and use our services.\n\n" +
                    "Best regards,\nBFI Bank";
            emailService.sendEmail(user.getEmail(), emailSubject, emailBody);
        }

        return ResponseEntity.ok(user);
    }
}

