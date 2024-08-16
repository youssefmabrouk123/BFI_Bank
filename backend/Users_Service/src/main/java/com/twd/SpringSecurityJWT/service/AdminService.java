//package com.twd.SpringSecurityJWT.service;
//
//import com.twd.SpringSecurityJWT.entity.OurUsers;
//import com.twd.SpringSecurityJWT.repository.OurUserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminService {
//
//    @Autowired
//    private OurUserRepo ourUserRepo;
//
//    public OurUsers unblockUser(Integer userId) {
//        OurUsers user = ourUserRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setBlocked(false);
//        return ourUserRepo.save(user);
//    }
//
//    public OurUsers blockUser(Integer userId) {
//        OurUsers user = ourUserRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setBlocked(true);
//        return ourUserRepo.save(user);
//    }
//
//    public OurUsers getUser(Integer userId) {
//        return ourUserRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}


package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private EmailService emailService;

    public OurUsers unblockUser(Integer userId) {
        OurUsers user = ourUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBlocked(false);
        OurUsers updatedUser = ourUserRepo.save(user);

        // Envoyer un e-mail
        String subject = "Votre compte a été activé";
        String text = "Bonjour " + user.getEmail() + ",\n\nVotre compte a été activé. Vous pouvez maintenant vous connecter.";
        emailService.sendEmail(user.getEmail(), subject, text);

        return updatedUser;
    }

    public OurUsers blockUser(Integer userId) {
        OurUsers user = ourUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBlocked(true);
        OurUsers updatedUser = ourUserRepo.save(user);

        // Envoyer un e-mail
        String subject = "Votre compte a été bloqué";
        String text = "Bonjour " + user.getEmail() + ",\n\nVotre compte a été bloqué. Veuillez contacter l'administrateur pour plus de détails.";
        emailService.sendEmail(user.getEmail(), subject, text);

        return updatedUser;
    }

    public OurUsers getUser(Integer userId) {
        return ourUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public OurUsers unblockUserByEmail(String email) {
        Optional<OurUsers> optionalUser = ourUserRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            OurUsers user = optionalUser.get();
            user.setBlocked(false); // Assuming `setBlocked` is a method to unblock the user
            ourUserRepo.save(user);
            return user;
        }
        return null; // Or handle this case as needed (e.g., throw an exception or return an empty result)
    }
}