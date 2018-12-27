package com.example.demo.service;

import com.example.demo.entity.PasswordRecord;

import java.util.Date;

public interface PasswordRecordService {
    boolean addPasswordRecord(PasswordRecord password_record);
    boolean deletePasswordRecord(Integer pId);
    String getPassword(String phone, Integer validateNumber, Date date);
}
