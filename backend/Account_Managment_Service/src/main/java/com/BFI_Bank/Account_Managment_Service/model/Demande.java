package com.BFI_Bank.Account_Managment_Service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GenerationTime;

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
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phoneNumber", unique = true, nullable = false)
    private String phoneNumber;
    private String adresse;
    private String pay;
    private String gouvernorat;
    private String codePostal;
    private Integer nombreEnfants;
    private String statutCivil;
    private String nationalite;
    //  private String offre;
    private String categorieSocioPro;
    private String revenuNetMensuel;
    private String natureActivite;
    private String secteurActivite;
    private String statut; // e.g., "PENDING", "APPROVED", "REJECTED"
    @Column(name = "numeroCin", unique = true)
    private Integer numeroCin;
    private String motDePasse ;
    private Date dateDelivrance;
    @Lob
    private byte[] cinFront;
    @Lob
    private byte[] cinBack;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation", updatable = false)
    private Date dateCreation;

    @PrePersist
    protected void onCreate() {
        dateCreation = new Date();
    }

    //private Integer userId;

}