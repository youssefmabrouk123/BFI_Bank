package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);

    Optional<OurUsers> findByVerificationToken(UUID verificationToken);

    Optional<OurUsers> findByPhoneNumber(String phoneNumber);
}

