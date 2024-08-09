package com.BFI_Bank.Account_Managment_Service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
//    @Lob
//    private byte[] cinFront;
//    @Lob
//    private byte[] cinBack;
    private Integer numeroCin;
    private Date dateDelivrance;
    private Integer clientId;

}
