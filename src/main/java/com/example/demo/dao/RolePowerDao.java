package com.example.demo.dao;

import com.example.demo.entity.RolePower;
import com.example.demo.entity.RolePowerCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePowerDao {
    void addRolePower(RolePower rolePower);
    void deleteRolePower(Integer rolePowerId);
    RolePowerCustom queryRolePowerById(Integer id);
    List<RolePowerCustom>  queryRolePowerByPowerId(Integer id);
    List<RolePowerCustom>  queryRolePowerByRoleId(Integer id);
    List<RolePowerCustom> queryRolePowerList();
    void batchAddRolePower (List<RolePower> rolePowerList);
}
