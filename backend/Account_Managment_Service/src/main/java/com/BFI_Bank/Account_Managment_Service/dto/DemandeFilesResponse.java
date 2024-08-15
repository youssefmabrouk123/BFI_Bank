package com.BFI_Bank.Account_Managment_Service.dto;
public class DemandeFilesResponse {
    private String cinBack;

    // Constructor
    public DemandeFilesResponse( String cinBack) {
        this.cinBack = cinBack;
    }

    // Getters and Setters

    public String getCinBack() {
        return cinBack;
    }

    public void setCinBack(String cinBack) {
        this.cinBack = cinBack;
    }
}
