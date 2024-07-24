package com.BFI_Bank.Account_Managment_Service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class CompteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String type;
    private double solde;
    private Date dateOuverture;
    private Long clientId;  // IdUser from OurUsers in Users Service
    private String statut;
}