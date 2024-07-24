package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody DemandeUserDto demande) {
        Demande createdDemande = demandeService.createDemande(demande);
        return ResponseEntity.ok(createdDemande);
    }
}
