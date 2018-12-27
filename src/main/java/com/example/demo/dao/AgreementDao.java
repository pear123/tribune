package com.example.demo.dao;

import com.example.demo.entity.Agreement;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementDao {
     void addAgreement(Agreement agreement);
     void updateAgreement(Agreement agreement);
     Agreement queryAgreementById(Integer id);
     void deleteAgreement(Integer id);
     Agreement queryAgreementByName(String name);
}
