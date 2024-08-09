package com.BFI_Bank.Account_Managment_Service.repository;

import com.BFI_Bank.Account_Managment_Service.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
    Optional<CompteBancaire> findByNumero(String numero);

}
