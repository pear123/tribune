package com.example.demo.service.impl;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.RolePowerDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePower;
import com.example.demo.entity.RolePowerCustom;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RolePowerDao rolePowerDao;


    @Override
    public boolean addRole(Role role) {
        if(role!=null){
            Role role1=roleDao.queryRoleByName(role.getRoleName());
            if(role1==null){
                roleDao.addRole(role);
                return true;
            }
        }
    return false;
    }

    
    /** 
    * @Description: 删除角色 
    * @Param: [roleId] 
    * @return: boolean
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public boolean deleteRole(Integer roleId) {
     Role role=roleDao.queryRoleById(roleId);
        List<User> userList=userDao.queryUserListByRoleId(roleId);
     if(role!=null && userList.isEmpty()){//角色是否为空，且用户是否被赋予这个角色
         List<RolePowerCustom> rolePowerList=rolePowerDao.queryRolePowerByRoleId(roleId);
         roleDao.deleteRole(roleId);
         if(!rolePowerList.isEmpty()){
             for(RolePowerCustom rolePowerCustom:rolePowerList){
                 rolePowerDao.deleteRolePower(rolePowerCustom.getRolePowerId());//删除关系表中的角色
             }
         }
       return true;
     }
     return false;
    }

    @Override
    public Role queryRoleById(Integer roleId) {
        return roleDao.queryRoleById(roleId);
    }

    @Override
    public List<Role> queryRoleList() {
        return roleDao.queryRoleList();
    }

    @Override
    public boolean updateRole(Role role) {
        Role role1=roleDao.queryRoleById(role.getRoleId());
        if(role1!=null){
                List<Role> roleList=roleDao.queryRoleList();
                for(Role role2:roleList){
                   if(role2.getRoleName().equals(role.getRoleName()) && !role2.getRoleName().equals(role1.getRoleName())){
                       return false;
                   }
                }
            roleDao.updateRole(role);
            return true;
        }
        return false;
    }

    @Override
    public Role queryRoleByName(String name) {
        return roleDao.queryRoleByName(name);
    }
}
