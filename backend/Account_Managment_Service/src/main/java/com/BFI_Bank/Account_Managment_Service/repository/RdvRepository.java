package com.BFI_Bank.Account_Managment_Service.repository;


import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {
    //List<Rdv> findByDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByDate(LocalDateTime date);
}