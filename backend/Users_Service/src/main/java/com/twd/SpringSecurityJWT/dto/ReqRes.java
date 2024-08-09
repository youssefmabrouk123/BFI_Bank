package com.twd.SpringSecurityJWT.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.twd.SpringSecurityJWT.entity.OurUsers;
//import com.twd.SpringSecurityJWT.entity.Product;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private String nom;
    private String prenom;
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String role;
    private String password;
   // private List<Product> products;
    private OurUsers ourUsers;
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


}
