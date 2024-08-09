package com.BFI_Bank.Account_Managment_Service.exception;

public class CarteException extends RuntimeException {
    public CarteException(String message) {
        super(message);
    }

    public CarteException(String message, Throwable cause) {
        super(message, cause);
    }
}
