package com.BFI_Bank.Account_Managment_Service.model;

import lombok.Data;

@Data
public class OurUsers {
    private Integer id;
    private String email;
    private String password;
    private String role;
    private boolean isBlocked;
    private boolean isEmailVerified;
    private String phoneNumber;
    private boolean isPhoneNumberVerified;
}
