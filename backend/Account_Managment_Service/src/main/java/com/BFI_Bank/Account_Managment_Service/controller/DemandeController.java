package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.exception.ErrorResponse;
import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.service.DemandeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Account/demandes")

public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/create")
    public ResponseEntity<?> createDemande(@RequestBody Demande demandeDto) {
        try {
            Demande savedDemande = demandeService.createDemande(demandeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDemande);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/accept/{idDemande}")
    public ResponseEntity<String> accepteDemande(@PathVariable Long idDemande) {
        try {
            String response = demandeService.accepteDemande(idDemande);
            if (response.equals("Demande Accepted")) {
                return ResponseEntity.ok(response);
            } else if (response.equals("Demande Failed")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Demande>> getAllDemandes() {
        try {
            List<Demande> demandes = demandeService.getAllDemandes();
            if (demandes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(demandes);
            } else {
                return ResponseEntity.ok(demandes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/refuse/{id}")
    public ResponseEntity<String> deleteDemande(@PathVariable Long id) {
        try {
            demandeService.deleteDemande(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Demande deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demande with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

}
