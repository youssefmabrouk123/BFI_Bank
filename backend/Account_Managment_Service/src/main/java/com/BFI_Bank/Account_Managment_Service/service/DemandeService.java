package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.dto.DocumentDto;
import com.BFI_Bank.Account_Managment_Service.feign.UsersServiceFeignClient;
import com.BFI_Bank.Account_Managment_Service.model.*;
import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
import com.BFI_Bank.Account_Managment_Service.repository.DocumentRepository;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;



@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UsersServiceFeignClient usersServiceFeignClient;

    @Autowired
    private CompteBancaireService compteBancaireService;

    @Autowired
    private DocumentService documentService;


    private JavaMailSender mailSender;



    @Autowired
    private  DocumentRepository documentRepo;
    private static final String UPLOAD_DIR = "C:\\Users\\dell\\Desktop\\BFI_BANK\\backend\\Account_Managment_Service\\CinBack";


    public Demande createDemande(DemandeUserDto demandeDto, MultipartFile cinFront, MultipartFile cinBack) throws Exception {
        if (demandeRepository.existsByEmail(demandeDto.getEmail())) {
            throw new Exception("Email already in use.");
        }
        if (demandeRepository.existsByPhoneNumber(demandeDto.getPhoneNumber())) {
            throw new Exception("Phone number already in use.");
        }
        if (demandeRepository.existsByNumeroCin(demandeDto.getNumeroCin())) {
            throw new Exception("CIN number already in use.");
        }

        Demande demande = new Demande();
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
        //demande.setOffre(demandeDto.getOffre());
        demande.setCategorieSocioPro(demandeDto.getCategorieSocioPro());
        demande.setRevenuNetMensuel(demandeDto.getRevenuNetMensuel());
        demande.setNatureActivite(demandeDto.getNatureActivite());
        demande.setSecteurActivite(demandeDto.getSecteurActivite());
        demande.setStatut("PENDING");
        demande.setNumeroCin(demandeDto.getNumeroCin());
        demande.setDateDelivrance(demandeDto.getDateDelivrance());
        if (cinFront != null) {
            demande.setCinFront(saveFile(cinFront));
        }
        if (cinBack != null) {
            demande.setCinBack(saveFile(cinBack));
        }
        demande.setMotDePasse(demandeDto.getMotDePasse());
        Demande demand=demandeRepository.save(demande);
        accepteDemande(demand.getId());
        return demand;
    }
    public String accepteDemande(Long idDemande) {
        try {
            Optional<Demande> optionalDemande = demandeRepository.findById(idDemande);
            if (optionalDemande.isPresent()) {
                Demande registrationRequest = optionalDemande.get();
                // Création d'un nouvel utilisateur OurUsers
                OurUsers ourUsers = new OurUsers();
                ourUsers.setDateNaissance(registrationRequest.getDateNaissance());
                ourUsers.setEmail(registrationRequest.getEmail());
                ourUsers.setPassword(registrationRequest.getMotDePasse());
                ourUsers.setRole("USER");
                ourUsers.setPhoneNumber("+216"+registrationRequest.getPhoneNumber());
                ourUsers.setAdresse(registrationRequest.getAdresse());
                ourUsers.setPay(registrationRequest.getPay());
                ourUsers.setGouvernorat(registrationRequest.getGouvernorat());
                ourUsers.setCodePostal(registrationRequest.getCodePostal());
                ourUsers.setNombreEnfants(registrationRequest.getNombreEnfants());
                ourUsers.setStatutCivil(registrationRequest.getStatutCivil());
                ourUsers.setNationalite(registrationRequest.getNationalite());
                //ourUsers.setOffre(registrationRequest.getOffre());
                ourUsers.setCategorieSocioPro(registrationRequest.getCategorieSocioPro());
                ourUsers.setRevenuNetMensuel(registrationRequest.getRevenuNetMensuel());
                ourUsers.setNatureActivite(registrationRequest.getNatureActivite());
                ourUsers.setSecteurActivite(registrationRequest.getSecteurActivite());
                ourUsers.setNom(registrationRequest.getNom());
                ourUsers.setPrenom(registrationRequest.getPrenom());

                // Appel du service Users_Service pour créer l'utilisateur et obtenir l'utilisateur créé
                OurUsers createdUser = usersServiceFeignClient.createUser(ourUsers);

                if (createdUser != null) {
                    Integer idClient = usersServiceFeignClient.getUserId(createdUser);
                    CompteBancaire compte = compteBancaireService.createCompteBancaireProfessionnel(
                            CompteBancaire.TypeCompte.COURANT, idClient, CompteBancaire.StatutCompte.ACTIF,createdUser.getNom(),createdUser.getPrenom());


                    //createDocumentFromDemande(registrationRequest,idClient);
                    // Création du document associé
//                    Document document = new Document();
////                    document.setCinBack(registrationRequest.getCinBack());
////                    document.setCinFront(registrationRequest.getCinFront());
//                    document.setDateDelivrance(registrationRequest.getDateDelivrance());
//                    document.setClientId(idClient);
//                    document.setNumeroCin(registrationRequest.getNumeroCin());
//                    document.setNom("CIN");
//
//                    // Log the document before saving
//                    System.out.println("Document to be saved: " + document);
//
//                    // Attempt to save the document
//                   Document savedDocument = documentRepo.save(document);

                    // Log the saved document
                    //System.out.println("Saved Document: " + savedDocument);

                    return "Demande Accepted";
                } else {
                    return "Demande Failed";
                }
            } else {
                throw new EntityNotFoundException("La demande avec l'ID " + idDemande + " n'existe pas.");
            }

        } catch (FeignException fe) {
            return "Error while creating user: " + fe.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Ensure the filename is valid and append the correct extension
        String originalFilename = Optional.ofNullable(file.getOriginalFilename())
                .filter(f -> !f.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Invalid file name"));

        // Extract file extension from content type
        String extension = "";
        String contentType = file.getContentType();
        if (contentType != null) {
            if (contentType.equals("image/jpeg")) {
                extension = ".jpg";
            } else if (contentType.equals("image/png")) {
                extension = ".png";
            } // Add more types if needed
        }

        String filename = originalFilename.contains(".")
                ? originalFilename.substring(0, originalFilename.lastIndexOf(".")) + extension
                : originalFilename + extension;

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(filename);

        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Debugging output
        System.out.println("File saved to: " + filePath.toString());

        return filePath.toString();
    }


    private Document createDocumentFromDemande(Demande registrationRequest, Integer idClient) {
        Document document = new Document();
        document.setNumeroCin(registrationRequest.getNumeroCin());
        document.setDateDelivrance(registrationRequest.getDateDelivrance());
        document.setClientId(idClient);
        document.setNom("CIN");
        documentService.createDocument(document);
        return document;
    }
    // Méthodes supplémentaires pour les fonctionnalités de lecture, mise à jour et suppression (CRUD)
    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
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
