package com.twd.SpringSecurityJWT.feign;


import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Account-Managment-Service", url = "http://localhost:8222")
public interface AccountManagementServiceFeignClient {
    @PostMapping("/api/v1/users/auth/signup")
    OurUsers createUser(@RequestBody OurUsers user);


    @PostMapping("/api/v1/users/auth/id")
    Integer getUserId(@RequestBody OurUsers user);

}