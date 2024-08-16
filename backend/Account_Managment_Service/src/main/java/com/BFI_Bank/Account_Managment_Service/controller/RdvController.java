package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import com.BFI_Bank.Account_Managment_Service.dto.RdvRequest;
import com.BFI_Bank.Account_Managment_Service.repository.DemandeRepository;
import com.BFI_Bank.Account_Managment_Service.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Account/rendezvous")

public class RdvController {


    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private RdvService rdvService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/available-slots")
    public List<LocalDateTime> getAvailableSlots() {
        return rdvService.getAvailableSlots();
    }

    @PostMapping("/book")
    public Rdv bookRdv(@RequestBody RdvRequest rdvRequest) {

        Rdv rdv = rdvService.createRdv(getIdByEmail(rdvRequest.getEmail()), rdvRequest.getSelectedDate());
        sendEmailWithRdvDetails(rdvRequest.getEmail(), rdv);
        return rdv;
    }
    public Long getIdByEmail(String email) {
        Optional<Long> idOptional = demandeRepository.findIdByEmail(email);
        return idOptional.orElseThrow(() -> new RuntimeException("No Demande found with email: " + email));
    }
    private void sendEmailWithRdvDetails(String email, Rdv rdv) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Appointment Details");
        message.setText("Your appointment is scheduled on " + rdvService.formatIsoDateTime( rdv.getDate().toString()) +
                ".\nGoogle Meet link: " + rdv.getLienMeet());
        mailSender.send(message);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Rdv>> getAllRdv() {
        List<Rdv> rdvList = rdvService.getAllRdv();
        return ResponseEntity.ok(rdvList);
    }

    @PostMapping("/setDone/{id}")
    public ResponseEntity<Rdv> setRdvDone(@PathVariable Long id) {
        Optional<Rdv> rdvOptional = rdvService.setRdvDone(id);
        return rdvOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}