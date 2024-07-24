package com.BFI_Bank.Account_Managment_Service.feign;

import com.BFI_Bank.Account_Managment_Service.model.OurUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-service", url = "http://localhost:8081")
public interface UsersServiceFeignClient {
    @PostMapping("/auth/signup")
    OurUsers createUser(@RequestBody OurUsers user);


    @PostMapping("/auth/id")
    Integer getUserId(@RequestBody OurUsers user);

}
