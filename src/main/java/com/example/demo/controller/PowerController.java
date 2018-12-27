package com.example.demo.controller;


import com.example.demo.entity.Power;
import com.example.demo.service.PowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("myPower")
public class PowerController {
    @Resource
    private PowerService powerService;

    /** 
    * @Description: 跳转到权限管理页面 
    * @Param: [model, uId] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2018/12/27 
    */
    @RequestMapping("powerManager")
    public String  powerManager(Model model,Integer uId)throws Exception {
        List<Power> powerList=powerService.queryPowerList();
        model.addAttribute("powerList",powerList);
        model.addAttribute("uId",uId);
        return "powerManager";
    }

    /** 
    * @Description: 创建权限 
    * @Param: [powerName] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2018/12/27 
    */
    @RequestMapping("createPower")
    public @ResponseBody
    String  createPower(String powerName)throws Exception {
        Power power=new Power();
        power.setPowerName(powerName);
       boolean b= powerService.addPower(power);
       if(b){
           return "success";
       }
        return "fail";
    }

    /** 
    * @Description: 删除权限 
    * @Param: [powerId] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2018/12/27 
    */
    @RequestMapping("deletePower")
    public @ResponseBody
    String   deletePower(Integer powerId)throws Exception {
        boolean b= powerService.deletePower(powerId);
        if(b){
            return "success";
        }
        return "fail";
    }
}
