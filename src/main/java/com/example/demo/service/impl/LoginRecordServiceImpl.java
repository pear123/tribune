package com.example.demo.service.impl;

import com.example.demo.dao.LoginRecordDao;
import com.example.demo.entity.LoginRecord;
import com.example.demo.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {
    @Autowired
    private LoginRecordDao loginRecordDao;

    /**
    * @Description: 登录记录的增加
    * @Param: [loginRecord]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean addRecord(LoginRecord loginRecord) {
        if(loginRecord!=null){
            if(loginRecord.getuPhone()!=null){
                boolean b=loginRecord.getuPhone().matches("[0-9]{1,}");//是否都为数字
                if(loginRecord.getuPhone().length()==11&&b){
                    loginRecordDao.addRecord(loginRecord);
                    return true;
                }
            }
        }
        return false;

    }
    /**
    * @Description: 登录记录的更新
    * @Param: [loginRecord]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean updateRecord(LoginRecord loginRecord) {
        LoginRecord loginRecord2=loginRecordDao.querryRecordByPhone(loginRecord.getuPhone());
        if(loginRecord2!=null){
            loginRecordDao.updateRecord(loginRecord);
            return true;
        }
        return false;

    }

    /**
    * @Description: 通过电话号码查找记录
    * @Param: [phone]
    * @return: com.example.demo.entity.LoginRecord
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */

    @Override
    public LoginRecord queryRecordByPhone(String phone) {
        LoginRecord record=loginRecordDao.querryRecordByPhone(phone);
        return record;
    }

    /**
     * @Description: 删除记录
     * @Param: [loginRecordId]
     * @return: boolean
     * @Author: Lili Chen
     * @Date: 2018/12/13
     */
    @Override
    public boolean deleteRecord(Integer loginRecordId) {
        LoginRecord loginRecord=loginRecordDao.querryRecordById(loginRecordId);
        if(loginRecord!=null){
            loginRecordDao.deleteRecord(loginRecordId);
            return true;
        }
        return false;
    }


}
