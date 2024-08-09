package com.BFI_Bank.Account_Managment_Service.controller;
import com.BFI_Bank.Account_Managment_Service.model.CarteProfessionnelle;
import com.BFI_Bank.Account_Managment_Service.service.CarteProfessionnelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/cartes-professionnelles")
public class CarteProfessionnelleController {

    private final CarteProfessionnelleService carteProfessionnelleService;

    @Autowired
    public CarteProfessionnelleController(CarteProfessionnelleService carteProfessionnelleService) {
        this.carteProfessionnelleService = carteProfessionnelleService;
    }

    @PostMapping
    public ResponseEntity<CarteProfessionnelle> createCarteProfessionnelle(
            @RequestParam Long compteBancaireId,
            @RequestParam String numeroCarte,
            @RequestParam String nomTitulaire,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateExpiration,
            @RequestParam String cvv,
            @RequestParam CarteProfessionnelle.TypeCarte typeCarte) {

        CarteProfessionnelle carteProfessionnelle = carteProfessionnelleService.createCarteProfessionnelle(
                compteBancaireId, numeroCarte, nomTitulaire, dateExpiration, cvv, typeCarte);

        return ResponseEntity.ok(carteProfessionnelle);
    }
}
