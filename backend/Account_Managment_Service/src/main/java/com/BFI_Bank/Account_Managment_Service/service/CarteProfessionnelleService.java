package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.model.CarteProfessionnelle;
import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import com.BFI_Bank.Account_Managment_Service.repository.CarteProfessionnelleRepository;
import com.BFI_Bank.Account_Managment_Service.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
public class CarteProfessionnelleService {

    @Autowired
    private CarteProfessionnelleRepository carteProfessionnelleRepository;

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    public CarteProfessionnelle createCarteProfessionnelle(Long compteBancaireId,
                                                           String numeroCarte,
                                                           String nomTitulaire,
                                                           Date dateExpiration,
                                                           String cvv,
                                                           CarteProfessionnelle.TypeCarte typeCarte) {
        Optional<CompteBancaire> compteBancaireOptional = compteBancaireRepository.findById(compteBancaireId);

        if (compteBancaireOptional.isEmpty()) {
            throw new IllegalArgumentException("Compte Bancaire not found with ID: " + compteBancaireId);
        }

        CompteBancaire compteBancaire = compteBancaireOptional.get();

        SecureRandom secureRandom = new SecureRandom();
        int pinCode = 1000 + secureRandom.nextInt(9000);



        CarteProfessionnelle carteProfessionnelle = new CarteProfessionnelle();
        carteProfessionnelle.setNumeroCarte(numeroCarte);
        carteProfessionnelle.setNomTitulaire(nomTitulaire);
        carteProfessionnelle.setDateExpiration(dateExpiration);
        carteProfessionnelle.setCvv(cvv);
        carteProfessionnelle.setTypeCarte(typeCarte);
        carteProfessionnelle.setCompteBancaire(compteBancaire);
        carteProfessionnelle.setDateEmission(new Date());
        carteProfessionnelle.setPinCode(pinCode);

        compteBancaire.setCarte(carteProfessionnelle);

        return carteProfessionnelleRepository.save(carteProfessionnelle);
    }
}
