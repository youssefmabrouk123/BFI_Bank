package com.BFI_Bank.Account_Managment_Service.repository;

import com.BFI_Bank.Account_Managment_Service.model.CarteProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarteProfessionnelleRepository extends JpaRepository<CarteProfessionnelle, Long> {
    //boolean existsByCardNumber(String cardNumber);
    boolean existsByNumeroCarte(String numeroCarte);

}
