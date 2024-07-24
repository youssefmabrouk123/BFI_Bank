package com.BFI_Bank.Account_Managment_Service.dto;


import java.time.LocalDateTime;

public class RdvRequest {
    private Integer clientId;
    private LocalDateTime selectedDate;
    private String email;

    // Getters and setters
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDateTime selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}