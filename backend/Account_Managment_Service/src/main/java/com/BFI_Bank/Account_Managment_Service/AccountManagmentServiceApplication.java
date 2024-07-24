package com.BFI_Bank.Account_Managment_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class AccountManagmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagmentServiceApplication.class, args);
	}

}
