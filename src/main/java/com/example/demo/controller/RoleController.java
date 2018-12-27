package com.example.demo.controller;


import com.example.demo.dao.RoleDao;
import com.example.demo.dao.RolePowerDao;
import com.example.demo.entity.*;
import com.example.demo.service.PowerService;
import com.example.demo.service.RolePowerService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("myRole")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PowerService powerService;
    @Resource
    private RolePowerService rolePowerService;
    @Resource
    private UserService userService;

    @RequestMapping("roleManager")
    public String roleManager(Model model, Integer uId)throws Exception {
        List<Role> roleList=roleService.queryRoleList();
        Iterator<Role> iterator=roleList.iterator();
        while(iterator.hasNext()){
            Role role=iterator.next();
            if(role.getRoleName().equals("管理员")||role.getRoleName().equals("普通会员")){
                iterator.remove();
            }
        }
        model.addAttribute("roleList",roleList);
        model.addAttribute("uId",uId);
        return "roleManager";
    }



    @RequestMapping("createRole")
    public @ResponseBody String createRole(Model model, String roleName)throws Exception {
        Role role=new Role();
        role.setRoleName(roleName);
       boolean b=roleService.addRole(role);
       if(b){
           return "success";
       }
       return "fail";
    }


    @RequestMapping("deleteRole")
    public @ResponseBody
    String  deleteRole(Integer roleId)throws Exception {
        boolean b= roleService.deleteRole(roleId);
        if(b){
            return "success";
        }
        return "fail";
    }
    @RequestMapping("editRole")
    public String editRole(Model model, Integer uId,Integer roleId)throws Exception {
        List<Power> powerList=powerService.queryPowerList();
        Role role=roleService.queryRoleById(roleId);
        List<RolePowerCustom> rolePowerList=rolePowerService.queryRolePowerByRoleId(roleId);
        List<String> myPowerIdList=new ArrayList<String>();
        for(RolePowerCustom rolePowerCustom:rolePowerList){
            myPowerIdList.add(rolePowerCustom.getPower().getPowerName());
        }

        model.addAttribute("uId",uId);
        model.addAttribute("powerList",powerList);
        model.addAttribute("role",role);
        model.addAttribute("powerNameList",myPowerIdList);
        return "editRole";
    }

    @RequestMapping("updateRole")
    public @ResponseBody String updateRole(Model model,String roleName,String powerList,Integer roleId)throws Exception {
        String[] powers = powerList.split(",");
        Role role=new Role();
        role.setRoleName(roleName);
        role.setRoleId(roleId);
        boolean a=roleService.updateRole(role);
        int count=0;
        boolean b=false;
       List<RolePower> rolePowerList=new ArrayList<RolePower>();
       if(powerList.length()!=0){
           for(String powerName:powers){
               Power power=powerService.queryPowerByName(powerName);//通过权限名获得权限对象
               RolePower rolePower=new RolePower();
               if(power!=null){//如果权限对象不为空，创建角色和权限先关系的对象
                   rolePower.setPowersId(power.getPowerId());
                   rolePower.setRolesId(roleId);
                   rolePowerList.add(rolePower);
               }
           }
       }
        b=rolePowerService.modifyRolePower(rolePowerList,roleId);

     if(a&b){
         return "success";
     }

        return "fail";
    }

    @RequestMapping(value ="/giveRole",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public @ResponseBody
    String  giveRole(Integer uId,String roleName,Model model){
        /*User user=userService.queryUserById(uId);*/
        Role role=roleService.queryRoleByName(roleName);
    /*    user.setRoleId(role.getRoleId());*/
        boolean b= false;
        try {
            b = userService.updateRole(uId,role.getRoleId());
        } catch (Exception e) {
           return "名字相同，无需修改";
        }
        if(b){
            return "success";
        }
        return "fail";
    }
}
