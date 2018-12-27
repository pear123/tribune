package com.example.demo.dao;


import com.example.demo.entity.PasswordRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecordDao {
    void addRecord(PasswordRecord passwordRecord);
    void updateRecord(PasswordRecord passwordRecord);
    PasswordRecord queryRecordByPhone(String uPhone);
    void deleteRecord(Integer pId);
    PasswordRecord queryRecordById(Integer pId);
}
