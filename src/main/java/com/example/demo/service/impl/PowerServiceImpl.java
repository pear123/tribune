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

    
    /** 
    * @Description: 创建权限 
    * @Param: [power] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
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

    
    /** 
    * @Description: 删除权限 
    * @Param: [powerId] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public boolean deletePower(Integer powerId) {
        Power power=powerDao.queryPowerById(powerId);
        if(power!=null){//power不为空
            List<RolePowerCustom> rolePowerCustomList=rolePowerDao.queryRolePowerByPowerId(powerId);
            if(rolePowerCustomList.isEmpty()){//查看权限是否被赋予给角色
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

    /** 
    * @Description: 查看权限表 
    * @Param: [] 
    * @return: java.util.List<com.example.demo.entity.Power> 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public List<Power> queryPowerList() {
        return powerDao.queryPowerList();
    }
}
