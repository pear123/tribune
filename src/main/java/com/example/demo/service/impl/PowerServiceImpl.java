package com.example.demo.service.impl;

import com.example.demo.dao.PowerDao;
import com.example.demo.dao.RolePowerDao;
import com.example.demo.entity.Power;
import com.example.demo.entity.RolePower;
import com.example.demo.entity.RolePowerCustom;
import com.example.demo.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerDao powerDao;
    @Autowired
    private RolePowerDao rolePowerDao;

    @Override
    public boolean addPower(Power power) {
     if(power!=null){
         Power power1=powerDao.queryPowerByName(power.getPowerName());
         if(power1==null){
             powerDao.addPower(power);
             return true;
         }
     }
     return false;
    }

    @Override
    public boolean deletePower(Integer powerId) {
        Power power=powerDao.queryPowerById(powerId);
        if(power!=null){//power不为空
            List<RolePowerCustom> rolePowerCustomList=rolePowerDao.queryRolePowerByPowerId(powerId);
            if(rolePowerCustomList.isEmpty()){
                powerDao.deletePower(powerId);
                return true;
            }
        }
        return false;
    }

    @Override
    public Power queryPowerById(Integer powerId) {
        return powerDao.queryPowerById(powerId);
    }

    @Override
    public Power queryPowerByName(String name) {
        return powerDao.queryPowerByName(name);
    }

    @Override
    public List<Power> queryPowerList() {
        return powerDao.queryPowerList();
    }
}
