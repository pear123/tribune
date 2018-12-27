package com.example.demo.service;

import com.example.demo.entity.RolePower;
import com.example.demo.entity.RolePowerCustom;

import java.util.List;

public interface RolePowerService {
    boolean addRolePower(RolePower rolePower);
    boolean  modifyRolePower(List<RolePower> rolePowerList,Integer roleId);
    List<RolePowerCustom> queryRolePowerByRoleId(Integer id);

}
