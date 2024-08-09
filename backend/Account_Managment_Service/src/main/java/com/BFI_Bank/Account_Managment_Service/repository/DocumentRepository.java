package com.BFI_Bank.Account_Managment_Service.repository;

import com.BFI_Bank.Account_Managment_Service.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {


}

