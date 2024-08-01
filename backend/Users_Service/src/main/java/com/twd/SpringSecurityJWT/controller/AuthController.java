package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<OurUsers> signUp(@RequestBody ReqRes signUpRequest){
        ReqRes signUpResponse = authService.signUp(signUpRequest);

        if (signUpResponse.getStatusCode() == 200) {
            OurUsers createdUser = signUpResponse.getOurUsers();
            return ResponseEntity.ok(createdUser);
        } else {
            // Handle error case, if needed
            return ResponseEntity.status(signUpResponse.getStatusCode()).build();
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/id")
    public Integer getUserId(@RequestBody OurUsers user) {
        return user.getId();
    }
}


//@PostMapping("/signup")
//public ResponseEntity<OurUsers> createUser(@RequestBody OurUsers signUpRequest){
//    OurUsers createdUser = authService.signUp(signUpRequest);
//    return ResponseEntity.ok(createdUser);
//}

