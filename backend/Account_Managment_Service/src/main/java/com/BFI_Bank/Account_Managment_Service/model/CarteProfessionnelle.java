package com.BFI_Bank.Account_Managment_Service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

@Data
@Entity
@Table(name = "cartes_professionnelles")
public class CarteProfessionnelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_carte", unique = true, nullable = false)
    private String numeroCarte;

    @Column(name = "nom_titulaire", nullable = false)
    private String nomTitulaire;

    @Column(name = "date_expiration", nullable = false)
    private Date dateExpiration;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_carte", nullable = false)
    private TypeCarte typeCarte; // Enum pour les types de cartes

    @JsonIgnore 
    @OneToOne
    @JoinColumn(name = "compte_bancaire_id")
    private CompteBancaire compteBancaire;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_emission", nullable = false)
    private Date dateEmission;

    @Column(name = "pin_code")
    private Integer pinCode;

    @PrePersist
    protected void onCreate() {
        if (dateEmission == null) {
            dateEmission = new Date();
        }

    }
    // Enum pour les types de cartes
    public enum TypeCarte {
        VISA,
        MASTERCARD,
        AMEX,
        OTHER
    }
}
