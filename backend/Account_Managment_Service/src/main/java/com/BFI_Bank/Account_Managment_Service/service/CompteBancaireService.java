package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import com.BFI_Bank.Account_Managment_Service.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class CompteBancaireService {

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    public CompteBancaire createCompteBancaireProfessionnel(String type, Long clientId, String statut) {
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setNumero(generateUnique16DigitNumero());
        compteBancaire.setType(type);
        compteBancaire.setSolde(0.0); // Initial balance is set to 0
        compteBancaire.setDateOuverture(new Date());
        compteBancaire.setClientId(clientId);
        compteBancaire.setStatut(statut);
        return compteBancaireRepository.save(compteBancaire);
    }

    private String generateUnique16DigitNumero() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            numero.append(digit);
        }
        return numero.toString();
    }
}
