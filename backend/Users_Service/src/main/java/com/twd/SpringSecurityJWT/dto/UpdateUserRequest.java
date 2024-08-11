package com.twd.SpringSecurityJWT.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String adresse;
    private String pay;
    private String gouvernorat;
    private String codePostal;
    private Integer nombreEnfants;
    private String statutCivil;
    private String categorieSocioPro;
    private String revenuNetMensuel;
    private String natureActivite;
    private String secteurActivite;
    private String phoneNumber;
    private String email;
}
