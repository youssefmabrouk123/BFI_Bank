package com.twd.SpringSecurityJWT.service;


import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.dto.UpdateUserRequest;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private OurUserRepo userRepository;
    public List<OurUsers> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<OurUsers> getUserByMail(String username) {

        return userRepository.findByEmail(username);
    }

    public OurUsers getUserById(Integer id) {
        Optional<OurUsers> userOptional = userRepository.findById(id);
        return userOptional.orElse(null); // Or handle the case where user is not found
    }

    public void userUpdate(OurUsers user) {
        // Save the post to the database using repository methods
        userRepository.save(user);
    }}
