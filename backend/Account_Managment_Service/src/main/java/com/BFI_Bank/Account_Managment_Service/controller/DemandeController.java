package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeFileFront;
import com.BFI_Bank.Account_Managment_Service.dto.DemandeFilesResponse;
import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.exception.CustomMultipartFile;
import com.BFI_Bank.Account_Managment_Service.exception.ErrorResponse;
import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.service.DemandeService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Account/demandes")

public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/create")
    public ResponseEntity<?> createDemande(@RequestBody DemandeUserDto demandeDto) {
        try {
            // Convert base64 to MultipartFile if needed
            MultipartFile cinFront = convertBase64ToMultipartFile(demandeDto.getCinFront(),"CinFront"+demandeDto.getNumeroCin().toString());
            MultipartFile cinBack = convertBase64ToMultipartFile(demandeDto.getCinBack(),"CinBack"+demandeDto.getNumeroCin());

            // Handle your request
            Demande savedDemande = demandeService.createDemande(demandeDto, cinFront, cinBack);
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

    private MultipartFile convertBase64ToMultipartFile(String base64, String filename) throws IOException {
        if (base64 == null || base64.isEmpty()) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        String contentType = "image/jpeg"; // You might want to set this based on the actual file type
        return new CustomMultipartFile("file", filename, contentType, decodedBytes);
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


    @GetMapping("/files/{idDemande}")
    public ResponseEntity<?> getDemandeFiles(@PathVariable Long idDemande) {
        try {
            //String cinFront = demandeService.getCinFront(idDemande);
            String cinBack = demandeService.getCinBack(idDemande);

            return ResponseEntity.ok(new DemandeFilesResponse( cinBack));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error reading file: " + e.getMessage()));
        }
    }


    @GetMapping("/files/front/{idDemande}")
    public ResponseEntity<?> getDemandeFilesFront(@PathVariable Long idDemande) {
        try {
            //String cinFront = demandeService.getCinFront(idDemande);
            String cinFront = demandeService.getCinFront(idDemande);

            return ResponseEntity.ok(new DemandeFileFront( cinFront));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error reading file: " + e.getMessage()));
        }
    }




}
