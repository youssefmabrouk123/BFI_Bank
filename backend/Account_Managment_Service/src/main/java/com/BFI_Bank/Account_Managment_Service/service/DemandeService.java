//package com.BFI_Bank.Account_Managment_Service.service;
//
//import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
//import com.BFI_Bank.Account_Managment_Service.feign.UsersServiceFeignClient;
//import com.BFI_Bank.Account_Managment_Service.model.Demande;
//import com.BFI_Bank.Account_Managment_Service.model.OurUsers;
//import com.BFI_Bank.Account_Managment_Service.model.Rdv;
//import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DemandeService {
//
//    @Autowired
//    private DemandeRepository demandeRepository;
//
//    @Autowired
//    private UsersServiceFeignClient usersServiceFeignClient;
//
//    @Autowired
//    private RdvService rdvService;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public Demande createDemande(DemandeUserDto demandeDto) {
//        // Créer une nouvelle instance OurUsers
//        OurUsers newUser = new OurUsers();
//        newUser.setEmail(demandeDto.getEmail());
//        newUser.setPassword(demandeDto.getMotDePasse());
//        newUser.setPhoneNumber(demandeDto.getPhoneNumber());
//        newUser.setRole("USER");
//
//        // Appeler Users_Service pour créer l'utilisateur et obtenir l'utilisateur créé
//        OurUsers createdUser = usersServiceFeignClient.createUser(newUser);
//
//        if (createdUser != null && createdUser.getId() != null) {
//            Demande demande = new Demande();
//            demande.setUserId(usersServiceFeignClient.getUserId(createdUser));
//            demande.setEmail(demandeDto.getEmail());
//            demande.setPhoneNumber(demandeDto.getPhoneNumber());
//            demande.setAdresse(demandeDto.getAdresse());
//            demande.setPay(demandeDto.getPay());
//            demande.setGouvernorat(demandeDto.getGouvernorat());
//            demande.setCodePostal(demandeDto.getCodePostal());
//            demande.setOffre(demandeDto.getOffre());
//            demande.setCategorieSocioPro(demandeDto.getCategorieSocioPro());
//            demande.setRevenuNetMensuel(demandeDto.getRevenuNetMensuel());
//            demande.setNatureActivite(demandeDto.getNatureActivite());
//            demande.setSecteurActivite(demandeDto.getSecteurActivite());
//            demande.setNumeroCIN(demandeDto.getNumeroCIN());
//            demande.setDateDelivranceCIN(demandeDto.getDateDelivranceCIN());
//            demande.setPhotoCINAvant(demandeDto.getPhotoCINAvant());
//            demande.setPhotoCINArriere(demandeDto.getPhotoCINArriere());
//            demande.setStatut(demandeDto.getStatut());
//            // Enregistrer la demande
//            Demande savedDemande = demandeRepository.save(demande);
//
//            Rdv rdv = rdvService.createRdv(usersServiceFeignClient.getUserId(createdUser));
//
//            sendEmailWithRdvDetails(createdUser.getEmail(), rdv);
//
//            return savedDemande;
//        } else {
//            throw new RuntimeException("Failed to create user");
//        }
//    }
//
//    private void sendEmailWithRdvDetails(String email, Rdv rdv) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your Appointment Details");
//        message.setText("Your appointment is scheduled on " + rdv.getDate() +
//                ".\nGoogle Meet link: " + rdv.getLienMeet());
//        mailSender.send(message);
//    }
//}
//


package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.dto.DemandeUserDto;
import com.BFI_Bank.Account_Managment_Service.feign.UsersServiceFeignClient;
import com.BFI_Bank.Account_Managment_Service.model.Demande;
import com.BFI_Bank.Account_Managment_Service.model.OurUsers;
import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UsersServiceFeignClient usersServiceFeignClient;

    @Autowired
    private RdvService rdvService;

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
            Demande demande = new Demande();
            demande.setUserId(usersServiceFeignClient.getUserId(createdUser));
            demande.setEmail(demandeDto.getEmail());
            demande.setPhoneNumber(demandeDto.getPhoneNumber());
            demande.setAdresse(demandeDto.getAdresse());
            demande.setPay(demandeDto.getPay());
            demande.setGouvernorat(demandeDto.getGouvernorat());
            demande.setCodePostal(demandeDto.getCodePostal());
            demande.setOffre(demandeDto.getOffre());
            demande.setCategorieSocioPro(demandeDto.getCategorieSocioPro());
            demande.setRevenuNetMensuel(demandeDto.getRevenuNetMensuel());
            demande.setNatureActivite(demandeDto.getNatureActivite());
            demande.setSecteurActivite(demandeDto.getSecteurActivite());
            demande.setNumeroCIN(demandeDto.getNumeroCIN());
            demande.setDateDelivranceCIN(demandeDto.getDateDelivranceCIN());
            demande.setStatut(demandeDto.getStatut());
            // Enregistrer la demande
            Demande savedDemande = demandeRepository.save(demande);

           // Rdv rdv = rdvService.createRdv(usersServiceFeignClient.getUserId(createdUser), selectedDate);

           // sendEmailWithRdvDetails(createdUser.getEmail(), rdv);

            return savedDemande;
        } else {
            throw new RuntimeException("Failed to create user");
        }
    }

    private void sendEmailWithRdvDetails(String email, Rdv rdv) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Appointment Details");
        message.setText("Your appointment is scheduled on " + rdv.getDate() +
                ".\nGoogle Meet link: " + rdv.getLienMeet());
        mailSender.send(message);
    }
}
