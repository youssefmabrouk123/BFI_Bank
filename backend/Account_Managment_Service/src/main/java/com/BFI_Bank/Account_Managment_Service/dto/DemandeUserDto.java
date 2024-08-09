package com.BFI_Bank.Account_Managment_Service.dto;


import jakarta.persistence.Lob;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    private String motDePasse ;

    private Date dateDelivrance;
    @Lob
    private byte[] cinFront;
    @Lob
    private byte[] cinBack;
}