package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.model.CarteProfessionnelle;
import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import com.BFI_Bank.Account_Managment_Service.repository.CarteProfessionnelleRepository;
import com.BFI_Bank.Account_Managment_Service.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@Service
public class CompteBancaireService {

    private final String secretKey = "mySecretKey";

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private CarteProfessionnelleRepository carteProfessionnelleRepository;



    @Autowired
    private CardNumberGeneratorService cardNumberGeneratorService;

    @Autowired
    private CarteProfessionnelleService carteProfessionnelleService;

    private static final int ACCOUNT_NUMBER_LENGTH = 16;

    public String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = generateRandomAccountNumber();
        } while (!isAccountNumberUnique(accountNumber));
        return accountNumber;
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    private boolean isAccountNumberUnique(String accountNumber) {
        return !compteBancaireRepository.findByNumero(accountNumber).isPresent();
    }

    public CompteBancaire createCompteBancaireProfessionnel(CompteBancaire.TypeCompte type, Integer clientId, CompteBancaire.StatutCompte statut) {
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setNumero(generateUniqueAccountNumber());
        compteBancaire.setType(type);
        compteBancaire.setSolde(0.0);
        compteBancaire.setDateOuverture(new Date());
        compteBancaire.setClientId(clientId);
        compteBancaire.setStatut(statut);

        // Sauvegarder le CompteBancaire d'abord pour générer un ID
        compteBancaire = compteBancaireRepository.save(compteBancaire);
        // Créer et associer une CarteProfessionnelle
        CarteProfessionnelle carteProfessionnelle = createCarteProfessionnelleForCompteBancaire(compteBancaire);
        compteBancaire.setCarte(carteProfessionnelle);

        return compteBancaire;
    }


    private CarteProfessionnelle createCarteProfessionnelleForCompteBancaire(CompteBancaire compteBancaire) {
        String number = cardNumberGeneratorService.generateUniqueCardNumber();
        CarteProfessionnelle carteProfessionnelle = new CarteProfessionnelle();
        carteProfessionnelle.setNumeroCarte(number);
        carteProfessionnelle.setNomTitulaire("Nom Titulaire");
        carteProfessionnelle.setDateExpiration(getDefaultExpirationDate());
        carteProfessionnelle.setCvv(generateCVV(number, convertDateToMMYY(getDefaultExpirationDate()), secretKey));
        System.out.println("7");


        carteProfessionnelle.setTypeCarte(CarteProfessionnelle.TypeCarte.VISA);

        carteProfessionnelle.setCompteBancaire(compteBancaire);


        carteProfessionnelleService.createCarteProfessionnelle(
                compteBancaire.getId(),
                carteProfessionnelle.getNumeroCarte(),
                carteProfessionnelle.getNomTitulaire(),
                carteProfessionnelle.getDateExpiration(),
                carteProfessionnelle.getCvv(),
                carteProfessionnelle.getTypeCarte()
        );
       // carteProfessionnelleRepository.save(carteProfessionnelle);

        return carteProfessionnelle;
    }


    public static String convertDateToMMYY(Date dateNormal) {
        SimpleDateFormat outputFormatter = new SimpleDateFormat("MM-yy");
        return outputFormatter.format(dateNormal);
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date getDefaultExpirationDate() {
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = today.plusYears(3).withDayOfMonth(today.lengthOfMonth());
        return java.sql.Date.valueOf(expirationDate);
    }

    private String generateCVV(String cardNumber, String expirationDate, String secretKey) {
        try {
            String data = cardNumber + expirationDate + secretKey;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            int cvv = ((hash[0] & 0xFF) << 16 | (hash[1] & 0xFF) << 8 | (hash[2] & 0xFF)) % 1000;
            return String.format("%03d", cvv);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors de la génération du CVV", e);
        }
    }
}
