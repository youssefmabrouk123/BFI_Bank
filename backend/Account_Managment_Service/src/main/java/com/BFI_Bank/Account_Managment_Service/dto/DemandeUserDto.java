package com.BFI_Bank.Account_Managment_Service.dto;


import lombok.Data;

@Data
public class DemandeUserDto {
    private Long id;
    private String email;
    private String motDePasse;
    private String phoneNumber;
    private String adresse;
    private String pay;
    private String gouvernorat;
    private String codePostal;
    private String offre;
    private String categorieSocioPro;
    private String revenuNetMensuel;
    private String natureActivite;
    private String secteurActivite;
    private String numeroCIN;
    private String dateDelivranceCIN;
    private String photoCINAvant;
    private String photoCINArriere;
    private String statut;
    private Integer userId;

}
