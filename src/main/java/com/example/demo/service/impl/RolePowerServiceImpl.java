package com.example.demo.service.impl;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.RolePowerDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePower;
import com.example.demo.entity.RolePowerCustom;
import com.example.demo.service.RolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePowerServiceImpl implements RolePowerService {
    @Autowired
     private RolePowerDao rolePowerDao;
    @Autowired
    private RoleDao roleDao;
    
    /** 
    * @Description: 添加权限关系 
    * @Param: [rolePower] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public boolean addRolePower(RolePower rolePower) {
        List<RolePowerCustom> rolePowerList=rolePowerDao.queryRolePowerList();
        if(!rolePowerList.isEmpty()){
            for(RolePowerCustom rolePowerCustom:rolePowerList){
                if(rolePowerCustom.getPowersId()==rolePower.getPowersId()&&rolePowerCustom.getRolesId()==rolePower.getRolesId()){
                    return false;
                }
            }
        }
        rolePowerDao.addRolePower(rolePower);
        return true;
    }

/** 
* @Description: 修改角色的权限（权限可能为批量）
* @Param: [rolePowerList, roleId] 
* @return: boolean 
* @Author: Lili Chen 
* @Date: 2018/12/26 
*/
    @Override
    public boolean modifyRolePower(List<RolePower> rolePowerList,Integer roleId){
        Role role=roleDao.queryRoleById(roleId);
        if(role!=null){
            List<RolePowerCustom> rolePowerCustomList=rolePowerDao.queryRolePowerByRoleId(roleId);
            if(!rolePowerCustomList.isEmpty()){//对角色之前之前的权限进行删除
                for(RolePowerCustom rolePowerCustom:rolePowerCustomList){
                    rolePowerDao.deleteRolePower(rolePowerCustom.getRolePowerId());
                }
            }
            if(!rolePowerList.isEmpty()){//添加权限
                rolePowerDao.batchAddRolePower(rolePowerList);
            }
            return true;
        }
       
       return false;
    }

    
    /** 
    * @Description: 通过角色id查看权限角色关系表 
    * @Param: [id] 
    * @return: java.util.List<com.example.demo.entity.RolePowerCustom> 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public List<RolePowerCustom> queryRolePowerByRoleId(Integer id) {
        Role role=roleDao.queryRoleById(id);
        List<RolePowerCustom> rolePowerCustomList=new ArrayList<RolePowerCustom>();
        if(role!=null){
           rolePowerCustomList=rolePowerDao.queryRolePowerByRoleId(id);
        }
        return rolePowerCustomList;
    }
}
