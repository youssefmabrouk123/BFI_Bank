package com.BFI_Bank.Account_Managment_Service.dto;


import lombok.Data;

import java.util.Date;

@Data
public class DocumentDto {
    private String nom;
    private byte[] cinFront;
    private byte[] cinBack;
    private Integer numeroCin;
    private Date dateDelivrance;

    // Getters and setters
}