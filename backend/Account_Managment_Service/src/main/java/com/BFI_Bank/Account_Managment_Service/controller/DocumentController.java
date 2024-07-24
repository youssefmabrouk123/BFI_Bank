package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.model.Document;
import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
import com.BFI_Bank.Account_Managment_Service.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(
            @RequestParam("demandeId") Long demandeId,
            @RequestParam("nom") String nom,
            @RequestParam("image") MultipartFile image) {
        try {
            Demande demande = demandeRepository.findById(demandeId)
                    .orElseThrow(() -> new RuntimeException("Demande not found with id: " + demandeId));

            Document document = new Document();
            document.setNom(nom);
            document.setImage(image.getBytes());
            document.setDemande(demande);

            documentRepository.save(document);

            return ResponseEntity.ok("Document uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading document");
        }
    }
}
