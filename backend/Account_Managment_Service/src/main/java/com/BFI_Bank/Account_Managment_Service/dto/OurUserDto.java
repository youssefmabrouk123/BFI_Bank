package com.BFI_Bank.Account_Managment_Service.dto;

import lombok.Data;

@Data
public class OurUserDto {
    private String id;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
}
