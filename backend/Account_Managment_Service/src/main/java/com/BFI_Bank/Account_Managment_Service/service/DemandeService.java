package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.dto.DocumentDto;
import com.BFI_Bank.Account_Managment_Service.feign.UsersServiceFeignClient;
import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.model.Document;
import com.BFI_Bank.Account_Managment_Service.model.OurUsers;
import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
import com.BFI_Bank.Account_Managment_Service.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UsersServiceFeignClient usersServiceFeignClient;

    @Autowired
    private JavaMailSender mailSender;

    public Demande createDemande(DemandeUserDto demandeDto) {
        // Créer une nouvelle instance OurUsers
        OurUsers newUser = new OurUsers();
        newUser.setEmail(demandeDto.getEmail());
        newUser.setPassword(demandeDto.getMotDePasse());
        newUser.setPhoneNumber(demandeDto.getPhoneNumber());
        newUser.setRole("USER");

        // Appeler Users_Service pour créer l'utilisateur et obtenir l'utilisateur créé
        OurUsers createdUser = usersServiceFeignClient.createUser(newUser);

        if (createdUser != null && createdUser.getId() != null) {
            // Créer la demande
            Demande demande = new Demande();
            demande.setUserId(usersServiceFeignClient.getUserId(createdUser));
            demande.setNom(demandeDto.getNom());
            demande.setPrenom(demandeDto.getPrenom());
            demande.setDateNaissance(demandeDto.getDateNaissance());
            demande.setEmail(demandeDto.getEmail());
            demande.setPhoneNumber(demandeDto.getPhoneNumber());
            demande.setAdresse(demandeDto.getAdresse());
            demande.setPay(demandeDto.getPay());
            demande.setGouvernorat(demandeDto.getGouvernorat());
            demande.setCodePostal(demandeDto.getCodePostal());
            demande.setNombreEnfants(demandeDto.getNombreEnfants());
            demande.setStatutCivil(demandeDto.getStatutCivil());
            demande.setNationalite(demandeDto.getNationalite());
            demande.setOffre(demandeDto.getOffre());
            demande.setCategorieSocioPro(demandeDto.getCategorieSocioPro());
            demande.setRevenuNetMensuel(demandeDto.getRevenuNetMensuel());
            demande.setNatureActivite(demandeDto.getNatureActivite());
            demande.setSecteurActivite(demandeDto.getSecteurActivite());
            demande.setStatut(demandeDto.getStatut());

            // Enregistrer la demande
            Demande savedDemande = demandeRepository.save(demande);

            // Gérer les documents associés
            List<Document> documents = new ArrayList<>();
            for (DocumentDto documentDto : demandeDto.getDocuments()) {
                Document document = new Document();
                document.setNom(documentDto.getNom());
                document.setCinFront(documentDto.getCinFront());
                document.setCinBack(documentDto.getCinBack());
                document.setNumeroCin(documentDto.getNumeroCin());
                document.setDateDelivrance(documentDto.getDateDelivrance());
                document.setDemande(savedDemande);
                documents.add(document);
            }
            documentRepository.saveAll(documents);

            // Optionnel: Envoyer un email de confirmation ou d'autres actions
            // sendEmailWithRdvDetails(createdUser.getEmail(), rdv);

            return savedDemande;
        } else {
            throw new RuntimeException("Failed to create user");
        }
    }

    // Méthodes supplémentaires pour les fonctionnalités de lecture, mise à jour et suppression (CRUD)
    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public Demande updateDemande(Long id, DemandeUserDto demandeDto) {
        Demande existingDemande = demandeRepository.findById(id).orElse(null);
        if (existingDemande != null) {
            // Mettre à jour les champs de la demande
            existingDemande.setNom(demandeDto.getNom());
            existingDemande.setPrenom(demandeDto.getPrenom());
            existingDemande.setDateNaissance(demandeDto.getDateNaissance());
            existingDemande.setEmail(demandeDto.getEmail());
            existingDemande.setPhoneNumber(demandeDto.getPhoneNumber());
            existingDemande.setAdresse(demandeDto.getAdresse());
            existingDemande.setPay(demandeDto.getPay());
            existingDemande.setGouvernorat(demandeDto.getGouvernorat());
            existingDemande.setCodePostal(demandeDto.getCodePostal());
            existingDemande.setNombreEnfants(demandeDto.getNombreEnfants());
            existingDemande.setStatutCivil(demandeDto.getStatutCivil());
            //existingDemande.setNationalite(demandeDto.getNationalite());
            existingDemande.setOffre(demandeDto.getOffre());
            existingDemande.setCategorieSocioPro(demandeDto.getCategorieSocioPro());
            existingDemande.setRevenuNetMensuel(demandeDto.getRevenuNetMensuel());
            existingDemande.setNatureActivite(demandeDto.getNatureActivite());
            existingDemande.setSecteurActivite(demandeDto.getSecteurActivite());
            existingDemande.setStatut(demandeDto.getStatut());
            // Mettre à jour les documents associés
            List<Document> documents = new ArrayList<>();
            for (DocumentDto documentDto : demandeDto.getDocuments()) {
                Document document = new Document();
                document.setNom(documentDto.getNom());
                document.setCinFront(documentDto.getCinFront());
                document.setCinBack(documentDto.getCinBack());
                document.setNumeroCin(documentDto.getNumeroCin());
                document.setDateDelivrance(documentDto.getDateDelivrance());
                document.setDemande(existingDemande);
                documents.add(document);
            }
            documentRepository.saveAll(documents);

            // Sauvegarder les modifications de la demande
            return demandeRepository.save(existingDemande);
        } else {
            return null; // Ou lancer une exception si la demande n'existe pas
        }
    }

    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }

    // Autres méthodes de service pour les besoins spécifiques

    private void sendEmailWithRdvDetails(String email, Rdv rdv) {
        // Code pour envoyer un email contenant les détails du rendez-vous
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Détails du rendez-vous");
        message.setText("Votre rendez-vous est prévu pour le " + rdv.getDate() +
                ". Veuillez utiliser ce lien pour accéder à la réunion: " + rdv.getLienMeet());
        mailSender.send(message);
    }

}
