package com.BFI_Bank.Account_Managment_Service.controller;


import com.BFI_Bank.Account_Managment_Service.model.Document;
import com.BFI_Bank.Account_Managment_Service.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Account/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Endpoint to save a new document
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        try {
            Document savedDocument = documentService.createDocument(document);
            return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
