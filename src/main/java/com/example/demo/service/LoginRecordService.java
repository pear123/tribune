package com.example.demo.service;

import com.example.demo.entity.LoginRecord;

public interface LoginRecordService {
    boolean addRecord(LoginRecord loginRecord);

    boolean updateRecord(LoginRecord loginRecord);

    LoginRecord queryRecordByPhone(String uPhone);

    boolean deleteRecord(Integer loginRecordId);

}
