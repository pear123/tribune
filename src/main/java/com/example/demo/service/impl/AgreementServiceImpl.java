package com.example.demo.service.impl;

import com.example.demo.dao.AgreementDao;
import com.example.demo.entity.Agreement;
import com.example.demo.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgreementServiceImpl implements AgreementService {
    @Autowired
    private AgreementDao agreementDao;
    @Override
    public Agreement queryAgreementByName(String name) {
        return agreementDao.queryAgreementByName(name);
    }
}
