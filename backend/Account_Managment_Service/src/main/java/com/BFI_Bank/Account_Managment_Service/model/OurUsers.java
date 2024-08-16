package com.BFI_Bank.Account_Managment_Service.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OurUsers {
    private Integer id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String pay;
    private String gouvernorat;
    private String codePostal;
    private Integer nombreEnfants;
    private String statutCivil;
    private String nationalite;
    private String offre;
    private String categorieSocioPro;
    private String revenuNetMensuel;
    private String natureActivite;
    private String secteurActivite;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;

    private Integer numeroCin;


    private boolean isBlocked;
    private boolean isEmailVerified;
    private UUID verificationToken;

    private boolean isPhoneNumberVerified;
}
