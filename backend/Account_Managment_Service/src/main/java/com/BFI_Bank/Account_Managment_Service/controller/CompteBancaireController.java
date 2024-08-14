package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import com.BFI_Bank.Account_Managment_Service.service.CompteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Account/comptes")
public class CompteBancaireController {

    @Autowired
    private CompteBancaireService compteBancaireService;

//    @PostMapping("/professionnel")
//    public ResponseEntity<CompteBancaire> createCompteBancaireProfessionnel(
//            @RequestParam CompteBancaire.TypeCompte type,
//            @RequestParam Integer clientId,
//            @RequestParam CompteBancaire.StatutCompte statut) {
//        CompteBancaire compteBancaire = compteBancaireService.createCompteBancaireProfessionnel(type, clientId, statut);
//        return ResponseEntity.ok(compteBancaire);
//    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<CompteBancaire> getCompteBancaireByClientId(@PathVariable Integer clientId) {
        return compteBancaireService.getCompteBancaireByClientId(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
