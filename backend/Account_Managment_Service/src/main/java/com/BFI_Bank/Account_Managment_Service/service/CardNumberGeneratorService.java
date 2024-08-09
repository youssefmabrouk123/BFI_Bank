package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.repository.CarteProfessionnelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardNumberGeneratorService {

    @Autowired
    private CarteProfessionnelleRepository cardRepository;

    private static final String BIN = "375078";
    private static final int MAX_RETRIES = 10;

    public String generateUniqueCardNumber() {
        String cardNumber;
        int retries = 0;

        do {
            cardNumber = generateCardNumber();
            retries++;
            } while (cardRepository.existsByNumeroCarte(cardNumber) && retries < MAX_RETRIES);

        if (retries == MAX_RETRIES) {
            throw new RuntimeException("Unable to generate a unique card number after " + MAX_RETRIES + " attempts");
        }

        return cardNumber;
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder(BIN);
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            cardNumber.append(random.nextInt(10));
        }
        int checkDigit = calculateLuhnCheckDigit(cardNumber.toString());
        cardNumber.append(checkDigit);
        return cardNumber.toString();
    }

    private int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}
