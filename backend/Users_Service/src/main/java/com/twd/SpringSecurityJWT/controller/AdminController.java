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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

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
}

