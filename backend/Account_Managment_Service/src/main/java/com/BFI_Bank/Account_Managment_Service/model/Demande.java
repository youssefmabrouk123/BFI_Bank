package com.BFI_Bank.Account_Managment_Service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String statut;
    private Integer userId;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;
}