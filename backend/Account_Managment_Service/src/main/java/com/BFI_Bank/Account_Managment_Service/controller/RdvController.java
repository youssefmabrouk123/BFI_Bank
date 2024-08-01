package com.BFI_Bank.Account_Managment_Service.controller;

import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import com.BFI_Bank.Account_Managment_Service.dto.RdvRequest;
import com.BFI_Bank.Account_Managment_Service.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Account/rendezvous")

public class RdvController {

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
        Rdv rdv = rdvService.createRdv(rdvRequest.getClientId(), rdvRequest.getSelectedDate());
        sendEmailWithRdvDetails(rdvRequest.getEmail(), rdv);
        return rdv;
    }

    private void sendEmailWithRdvDetails(String email, Rdv rdv) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Appointment Details");
        message.setText("Your appointment is scheduled on " + rdvService.formatIsoDateTime( rdv.getDate().toString()) +
                ".\nGoogle Meet link: " + rdv.getLienMeet());
        mailSender.send(message);
    }
}