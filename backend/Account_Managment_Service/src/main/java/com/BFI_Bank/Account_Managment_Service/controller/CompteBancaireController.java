package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import com.BFI_Bank.Account_Managment_Service.service.CompteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

    @PutMapping("/{id}/update-last-connexion")
    public CompteBancaire updateLastConnexion(@PathVariable Long id) {
        return compteBancaireService.updateLastConnexion(id);
    }


    @PostMapping("/{id}/save-signature")
    public CompteBancaire saveSignature(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String signatureBase64 = requestBody.get("signature");
        return compteBancaireService.saveSignature(id, signatureBase64);
    }

    @GetMapping("/{id}/signature")
    public Map<String, String> getSignature(@PathVariable Long id) {
        Optional<CompteBancaire> optionalCompte = compteBancaireService.getCompteBancaireById(id);
        if (optionalCompte.isPresent()) {
            String signatureBase64 = optionalCompte.get().getSignature();
            return Collections.singletonMap("signature", signatureBase64);
        } else {
            throw new RuntimeException("CompteBancaire not found");
        }
    }
}
