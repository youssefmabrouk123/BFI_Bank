package com.BFI_Bank.Account_Managment_Service.dto;

import com.BFI_Bank.Account_Managment_Service.model.CarteProfessionnelle;
import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire.StatutCompte;
import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire.TypeCompte;
import lombok.Data;

import java.util.Date;

@Data
public class CompteBancaireDTO {

    private Long id;
    private String numero;
    private TypeCompte type;
    private double solde;
    private Date dateOuverture;
    private Integer clientId;
    private StatutCompte statut;
    private Date lastConnexion;
    private Integer transactionNumber;
    private Boolean contractSignature;

    private CarteProfessionnelle carte;

    // Add other fields as necessary, but exclude the signature field
}
