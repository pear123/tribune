package com.example.demo.service.impl;

import com.example.demo.dao.PasswordRecordDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.PasswordRecord;
import com.example.demo.entity.User;
import com.example.demo.service.PasswordRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PasswordRecordServiceImpl implements PasswordRecordService {
    @Autowired
    private PasswordRecordDao passwordRecordDao;
    @Autowired
    private UserDao userDao;
    
    
    /** 
    * @Description: 添加更改密码记录 
    * @Param: [passwordRecord]
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2018/12/17 
    */
    @Override
    public boolean addPasswordRecord(PasswordRecord passwordRecord) {
        User user=userDao.queryUserByPhone(passwordRecord.getuPhone());
        PasswordRecord passwordRecord1=passwordRecordDao.queryRecordByPhone(passwordRecord.getuPhone());
        if(user!=null){
            if(passwordRecord1==null){
                System.out.println("密码记录为空");
                passwordRecordDao.addRecord(passwordRecord);
                return true;
            }else{
                System.out.println("密码记录不为空");
                passwordRecordDao.deleteRecord(passwordRecord1.getpId());//先删除原来的找回记录
                passwordRecordDao.addRecord(passwordRecord);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePasswordRecord(Integer pId) {
        PasswordRecord passwordRecord=passwordRecordDao.queryRecordById(pId);
        if(passwordRecord!=null){
           passwordRecordDao.deleteRecord(pId);
            return true;
        }
        return false;
    }

    /**
    * @Description: 更改密码时进行验证码核对 （找回密码）
    * @Param: [phone, validateNumber, date]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @Override
    public String getPassword(String phone, Integer validateNumber, Date date) {
        User user=userDao.queryUserByPhone(phone);
        if(user!=null){
            PasswordRecord passwordRecord=passwordRecordDao.queryRecordByPhone(phone);
            if(passwordRecord!=null){
                int c=(int)((date.getTime()-passwordRecord.getpCreatetime().getTime())/1000);
                if(c<=60){
                    if(passwordRecord.getuPhone().equals(phone)&&passwordRecord.getValidateNumber().equals(validateNumber)){
                       passwordRecordDao.deleteRecord(passwordRecord.getpId());
                        return user.getPassword();
                    }
                }
            }
        }
        return "";
    }
}
