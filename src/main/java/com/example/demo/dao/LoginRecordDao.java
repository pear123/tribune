package com.example.demo.dao;

import com.example.demo.entity.LoginRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRecordDao {
     void addRecord(LoginRecord loginRecord);

     void updateRecord(LoginRecord loginRecord);

     LoginRecord querryRecordByPhone(String uPhone);

     void deleteRecord(Integer loginRecordId);

     LoginRecord querryRecordById(Integer loginRecordId);
}
