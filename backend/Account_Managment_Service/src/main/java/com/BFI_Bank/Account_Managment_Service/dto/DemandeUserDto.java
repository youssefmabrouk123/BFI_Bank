package com.BFI_Bank.Account_Managment_Service.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class DemandeUserDto {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private String phoneNumber;
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
    private Integer numeroCin;
    private String motDePasse;
    private Date dateDelivrance;

    private String cinFront;
    private String cinBack;
}