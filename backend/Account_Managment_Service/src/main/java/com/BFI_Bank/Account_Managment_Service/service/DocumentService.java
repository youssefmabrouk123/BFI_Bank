package com.BFI_Bank.Account_Managment_Service.service;


import com.BFI_Bank.Account_Managment_Service.model.Document;
import com.BFI_Bank.Account_Managment_Service.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // Créer un nouveau document
    public Document createDocument(Document document) {
        // Validation des champs obligatoires
        if (document.getNom() == null || document.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom du document est obligatoire.");
        }
//        if (document.getCinFront() == null || document.getCinFront().length == 0) {
//            throw new IllegalArgumentException("Le recto de la CIN est obligatoire.");
//        }
//        if (document.getCinBack() == null || document.getCinBack().length == 0) {
//            throw new IllegalArgumentException("Le verso de la CIN est obligatoire.");
//        }
        if (document.getNumeroCin() == null) {
            throw new IllegalArgumentException("Le numéro de CIN est obligatoire.");
        }
        if (document.getDateDelivrance() == null) {
            throw new IllegalArgumentException("La date de délivrance est obligatoire.");
        }
        if (document.getClientId() == null) {
            throw new IllegalArgumentException("L'ID du client est obligatoire.");
        }
//
//        // Vérification de la validité du numéro de CIN
//        if (!isValidCinNumber(document.getNumeroCin())) {
//            throw new IllegalArgumentException("Le numéro de CIN est invalide.");
//        }
//
//        // Vérification de la validité de la date de délivrance
//        if (document.getDateDelivrance().after(new Date())) {
//            throw new IllegalArgumentException("La date de délivrance ne peut pas être dans le futur.");
//        }

        // Sauvegarde du document dans la base de données
        return documentRepository.save(document);
    }

    private boolean isValidCinNumber(Integer numeroCin) {
        // Implémentez ici la logique de validation du numéro de CIN
        // Par exemple, vérifiez le format ou la longueur du numéro
        return numeroCin.toString().length() == 8; // Exemple de vérification pour un numéro de CIN à 8 chiffres
    }

    // Obtenir un document par son ID
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    // Obtenir tous les documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Mettre à jour un document existant
    public Document updateDocument(Long id, Document updatedDocument) {
        return documentRepository.findById(id).map(document -> {
            document.setNom(updatedDocument.getNom());
//            document.setCinFront(updatedDocument.getCinFront());
//            document.setCinBack(updatedDocument.getCinBack());
            document.setNumeroCin(updatedDocument.getNumeroCin());
            document.setDateDelivrance(updatedDocument.getDateDelivrance());
            document.setClientId(updatedDocument.getClientId());
            return documentRepository.save(document);
        }).orElseThrow(() -> new RuntimeException("Document not found with id " + id));
    }

    // Supprimer un document
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}