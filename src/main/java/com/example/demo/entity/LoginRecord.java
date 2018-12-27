package com.example.demo.entity;

import java.util.Date;

public class LoginRecord {
    private Integer loginRecordId;
    private String uPhone;
    private Date loginDate;
    private Integer failNum;
    private String lockFlag;

    public Integer getLoginRecordId() {
        return loginRecordId;
    }

    public void setLoginRecordId(Integer loginRecordId) {
        this.loginRecordId = loginRecordId;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public LoginRecord() {
    }
}
