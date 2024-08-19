package com.BFI_Bank.Account_Managment_Service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "comptes_bancaires")
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero; // Peut être généré automatiquement

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCompte type;

    @Column(nullable = false)
    private double solde;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_ouverture", updatable = false)
    private Date dateOuverture;

    @Column(name = "client_id")
    private Integer clientId;  // IdUser from OurUsers in Users Service

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCompte statut;

    @Column(name = "lastConnexion")
    private Date lastConnexion ;

    @Column(name = "transactionNumber")
    private Integer transactionNumber;

    @OneToOne(mappedBy = "compteBancaire", cascade = CascadeType.ALL)
    private CarteProfessionnelle carte;

    @Column(name = "contractSignature")
        private Boolean contractSignature ;

    @Lob
        private String signature;

    @PrePersist
    protected void onCreate() {
        if (dateOuverture == null) {
            dateOuverture = new Date();
        }
    }

    // Enumération pour les types de compte
    public enum TypeCompte {
        COURANT,
        EPARGNE,
        ENTREPRISE
    }

    // Enumération pour les statuts de compte
    public enum StatutCompte {
        ACTIF,
        INACTIF
    }
}
