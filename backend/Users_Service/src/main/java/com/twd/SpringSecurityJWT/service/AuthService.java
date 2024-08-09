package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            OurUsers ourUsers = new OurUsers();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            ourUsers.setPhoneNumber(registrationRequest.getPhoneNumber());
            ourUsers.setAdresse(registrationRequest.getAdresse());
            ourUsers.setPay(registrationRequest.getPay());
            ourUsers.setGouvernorat(registrationRequest.getGouvernorat());
            ourUsers.setCodePostal(registrationRequest.getCodePostal());
            ourUsers.setNombreEnfants(registrationRequest.getNombreEnfants());
            ourUsers.setStatutCivil(registrationRequest.getStatutCivil());
            ourUsers.setNationalite(registrationRequest.getNationalite());
            ourUsers.setOffre(registrationRequest.getOffre());
            ourUsers.setCategorieSocioPro(registrationRequest.getCategorieSocioPro());
            ourUsers.setRevenuNetMensuel(registrationRequest.getRevenuNetMensuel());
            ourUsers.setNatureActivite(registrationRequest.getNatureActivite());
            ourUsers.setSecteurActivite(registrationRequest.getSecteurActivite());
            ourUsers.setNom(registrationRequest.getNom());
            ourUsers.setPrenom(registrationRequest.getPrenom());
            ourUsers.setBlocked(true);

            OurUsers ourUserResult = ourUserRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId() > 0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }


    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            OurUsers user = ourUserRepo.findByEmail(signinRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.isBlocked()) {
                response.setStatusCode(403); // Forbidden
                response.setMessage("Your account is not yet activated. Please contact admin.");
                return response;
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        } catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
