package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailVerificationService {

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(OurUsers user) {
        String verificationToken = generateVerificationToken(user);

        String emailContent = "Dear " + user.getEmail() + ",\n\n"
                + "Your verification token is: " + verificationToken + "\n\n"
                + "Best regards,\nYour Application Team";

        sendEmail(user.getEmail(), "Email Verification", emailContent);
    }

    public OurUsers findByEmail(String email) {
        return ourUserRepo.findByEmail(email).orElse(null);
    }

    public boolean verifyEmailToken(String verificationToken) {
        UUID tokenUUID;
        try {
            tokenUUID = UUID.fromString(verificationToken);
        } catch (IllegalArgumentException e) {
            return false; // Token is not a valid UUID
        }

        OurUsers user = ourUserRepo.findByVerificationToken(tokenUUID).orElse(null);
        if (user == null) {
            return false; // No user found for this token
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        ourUserRepo.save(user); // Save user with email verified status
        return true;
    }

    private String generateVerificationToken(OurUsers user) {
        UUID token = UUID.randomUUID();
        user.setVerificationToken(token);
        ourUserRepo.save(user); // Save token to user entity
        return token.toString();
    }

    private void sendEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
